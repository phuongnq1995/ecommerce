package com.ecommerce.product.validation;

import com.ecommerce.product.dto.form.ProductForm;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.CategoryRepository;
import com.ecommerce.product.repository.EcommerceImageRepository;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.web.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
@Slf4j
public class ProductValidator {

	private static final String ENTITY_NAME = Product.class.getSimpleName();
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final EcommerceImageRepository ecommerceImageRepository;

	public ProductValidator(
		ProductRepository productRepository, CategoryRepository categoryRepository,
		EcommerceImageRepository ecommerceImageRepository) {

		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.ecommerceImageRepository = ecommerceImageRepository;
	}

	public void validateCreateProduct(ProductForm product) {

		if (Objects.nonNull(product.getId())) {
			throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
		}

		if (Objects.isNull(product.getCategoryId())) {
			throw new BadRequestAlertException("Category Id must be provided", ENTITY_NAME, "categoryidnotexists");

		} else if (!categoryRepository.existsById(product.getCategoryId())) {
			throw new BadRequestAlertException("Invalid categoryId", ENTITY_NAME, "categoryidnotexists");
		}

		boolean existsImageIds = ecommerceImageRepository.existsAllByIdIn(Arrays.asList(product.getImageIds()));

		if (!existsImageIds) {
			throw new BadRequestAlertException("Invalid imageIds", ENTITY_NAME, "imageidsnotexists");
		}
	}

}
