package com.ecommerce.product.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.ObjectMetadataProvider;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.ecommerce.product.dto.FileInfo;
import com.ecommerce.product.properties.ApplicationProperties;
import com.ecommerce.product.service.FileStorageService;
import com.ecommerce.product.utils.EcommerceFileUtils;
import com.ecommerce.product.web.errors.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.ecommerce.product.constant.ApplicationConstants.Folder;

@Service
@Slf4j
public class S3StorageService implements FileStorageService {

	private final AmazonS3 amazonS3Client;
	private final ApplicationProperties applicationProperties;

	public S3StorageService(AmazonS3 amazonS3Client, ApplicationProperties applicationProperties) {

		this.amazonS3Client = amazonS3Client;
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void uploadFile(String keyName, MultipartFile file) {
		try {

			ObjectMetadata metadata = new ObjectMetadata();

			metadata.setContentLength(file.getSize());

			amazonS3Client.putObject(
				applicationProperties.getRemoteStorage().getBucketName(), keyName, file.getInputStream(), metadata);

		} catch (IOException ioe) {
			log.error("IOException: " + ioe.getMessage());
		} catch (AmazonServiceException serviceException) {
			log.info("AmazonServiceException: " + serviceException.getMessage());
			throw serviceException;
		} catch (AmazonClientException clientException) {
			log.info("AmazonClientException Message: " + clientException.getMessage());
			throw clientException;
		}
	}

	@Override
	public List<FileInfo> uploadFiles(MultipartFile[] files) {

		long startTime = System.currentTimeMillis();

		try {

			Path tempFolderPath = createTempFolder(files, startTime);

			File fileFolder = tempFolderPath.toFile();

			String s3FolderPath =
				String.format("%s/%s", Folder.TEMP_PRODUCT.getName(), tempFolderPath.getFileName());

			log.info("Starting upload files...");

			TransferManager tx = TransferManagerBuilder.standard()
				.withS3Client(amazonS3Client)
				.withDisableParallelDownloads(false)
				.withMultipartCopyPartSize(102400L)
				.withMultipartCopyThreshold(512000L)
				.withExecutorFactory(() -> Executors.newFixedThreadPool(2))
				.build();

			// Expiration time is after 1 day
			ObjectMetadataProvider metadataProvider =
				(file, metadata) -> metadata.setExpirationTime(
					Date.from(Instant.now().plus(1, ChronoUnit.DAYS)));

			MultipleFileUpload multipleFileUpload = tx.uploadDirectory(
				applicationProperties.getRemoteStorage().getBucketName(), s3FolderPath, fileFolder,
				false, metadataProvider);

			multipleFileUpload.waitForCompletion();

			tx.shutdownNow(false);

			long elapsedTime = System.currentTimeMillis() - startTime;

			log.info("Finished update files... in {}s. ", elapsedTime / 1000);

			List<FileInfo> fileInfos = Arrays.stream(Objects.requireNonNull(fileFolder.listFiles()))
				.map(file -> new FileInfo(file.getName(), s3FolderPath, FilenameUtils.getExtension(file.getName()), file.length()))
				.collect(Collectors.toList());

			// Remove temp folder in local
			EcommerceFileUtils.deleteFolder(tempFolderPath);

			return fileInfos;

		} catch (Exception e) {

			log.error("Exception while uploading files", e);

			throw new FileStorageException("Exception while uploading files", e);
		}
	}

	public List<S3ObjectSummary> getS3Objects(String folder, boolean mounted) {
		ObjectListing objectListing = amazonS3Client.listObjects(
			new ListObjectsRequest()
				.withBucketName(applicationProperties.getRemoteStorage().getBucketName())
				.withPrefix(folder)
				.withDelimiter("/"));

		List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

		if (!mounted) {
			objectSummaries.removeIf(s3ObjectSummary -> s3ObjectSummary.getKey().equals(folder));
		}

		return objectSummaries;
	}

	private Path createTempFolder(MultipartFile[] files, long createdTime) {

		String localFolderPath =
			String.format("%d-%s", createdTime, UUID.randomUUID());

		Path tempProductFolder = Paths.get(applicationProperties.getLocalStoragePath())
			.resolve(Folder.TEMP_PRODUCT.getName());

		Path tempGenerateFolder = EcommerceFileUtils.createSubFile(tempProductFolder, localFolderPath);

		Arrays.stream(files).forEach(file -> {

			String fileName = StringUtils.cleanPath(
				Optional.ofNullable(file.getOriginalFilename())
					.orElse(file.getName())
			);

			try {

				Path targetLocation = tempGenerateFolder.resolve(fileName);

				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException ex) {

				throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
			}
		});

		return tempGenerateFolder;
	}

}
