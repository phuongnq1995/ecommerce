package com.ecommerce.product.service.impl;

import com.ecommerce.product.dto.FileInfo;
import com.ecommerce.product.entity.EcommerceImage;
import com.ecommerce.product.properties.ApplicationProperties;
import com.ecommerce.product.repository.EcommerceImageRepository;
import com.ecommerce.product.service.FileStorageService;
import com.ecommerce.product.service.ImageService;
import com.ecommerce.product.utils.EcommerceFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ImageServiceImpl implements ImageService {

	private final EcommerceImageRepository ecommerceImageRepository;
	private final FileStorageService fileStorageService;
	private final ApplicationProperties applicationProperties;
	private final ModelMapper modelMapper;

	public ImageServiceImpl(
		EcommerceImageRepository ecommerceImageRepository,
		FileStorageService fileStorageService, ApplicationProperties applicationProperties, ModelMapper modelMapper) {

		this.ecommerceImageRepository = ecommerceImageRepository;
		this.fileStorageService = fileStorageService;
		this.applicationProperties = applicationProperties;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<EcommerceImage> saveEcommerceImages(MultipartFile[] files) {

		List<EcommerceImage> ecommerceImages = fileStorageService.uploadFiles(files)
			.stream()
			.map(this::getEcommerceImage)
			.peek(System.out::println)
			.collect(Collectors.toList());

		return ecommerceImageRepository.saveAll(ecommerceImages);
	}

	private EcommerceImage getEcommerceImage(FileInfo fileInfo) {

		EcommerceImage ecommerceImage = modelMapper.map(fileInfo, EcommerceImage.class);

		String url = applicationProperties.getRemoteStorage().getPublicURL() + "/"
			+ fileInfo.getFileFolder() + "/" + EcommerceFileUtils.encodeURL(fileInfo.getFileName());

		ecommerceImage.setFileDownloadUri(url);

		return ecommerceImage;
	}
}
