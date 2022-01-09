package com.ecommerce.product.service;

import com.ecommerce.product.entity.EcommerceImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

	List<EcommerceImage> saveEcommerceImages(MultipartFile[] files);

}
