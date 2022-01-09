package com.ecommerce.product.service;

import com.ecommerce.product.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

	List<Category> get();

	List<? extends Category> getHierarchy();

	Optional<Category> get(UUID id);

	Category create(Category category);

	Category update(UUID id, Category category);

	void delete(UUID id);
}
