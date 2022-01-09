package com.ecommerce.product.service;

import com.ecommerce.product.dto.form.ProductForm;
import com.ecommerce.product.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

	Optional<Product> get(UUID id);

	Product create(ProductForm product);
}
