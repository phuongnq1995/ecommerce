package com.ecommerce.product.validation;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.repository.CategoryRepository;
import com.ecommerce.product.web.errors.BadRequestAlertException;
import com.ecommerce.product.web.errors.NotFoundAlertException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class CategoryValidator {

	private static final String ENTITY_NAME = Category.class.getSimpleName();
	private final CategoryRepository categoryRepository;

	public CategoryValidator(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void validateCreateCategory(Category category) {

		if (Objects.nonNull(category.getId())) {

			throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
		}
	}

	public void validateUpdateCategory(UUID id, Category category) {

		if (category.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}

		if (!Objects.equals(id, category.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!categoryRepository.existsById(id)) {
			throw new NotFoundAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}
	}

	public void validateDeleteCategory(UUID id) {

		if (!categoryRepository.existsById(id)) {
			throw new NotFoundAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}
	}
}
