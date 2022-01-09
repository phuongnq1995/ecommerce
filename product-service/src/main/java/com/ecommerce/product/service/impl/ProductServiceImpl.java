package com.ecommerce.product.service.impl;

import com.ecommerce.product.dto.form.ProductForm;
import com.ecommerce.product.entity.EcommerceImage;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.EcommerceImageRepository;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.PriceService;
import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.validation.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final EcommerceImageRepository ecommerceImageRepository;
	private final PriceService priceService;
	private final ProductValidator productValidator;
	private final ModelMapper modelMapper;

	public ProductServiceImpl(
		ProductRepository productRepository, ProductValidator productValidator,
		EcommerceImageRepository ecommerceImageRepository, PriceService priceService, ModelMapper modelMapper) {

		this.productRepository = productRepository;
		this.productValidator = productValidator;
		this.ecommerceImageRepository = ecommerceImageRepository;
		this.priceService = priceService;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Product> get(UUID id) {
		return productRepository.findById(id);
	}

	@Override
	public Product create(ProductForm productForm) {

		System.out.println("productForm:" + productForm);

		productValidator.validateCreateProduct(productForm);

		List<EcommerceImage> ecommerceImageList =
			ecommerceImageRepository.getAllByIdIn(Arrays.asList(productForm.getImageIds()));

		Product product = modelMapper.map(productForm, Product.class);

		product.setImages(ecommerceImageList);

		product = productRepository.save(product);

		priceService.createPrice(product.getId(), product.getPrice());

		return product;
	}

}
