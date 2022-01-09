package com.ecommerce.product.service;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.ecommerce.product.dto.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

	void uploadFile(String keyName, MultipartFile file);

	List<FileInfo> uploadFiles(MultipartFile[] files);

	List<S3ObjectSummary> getS3Objects(String folder, boolean mounted);
}
