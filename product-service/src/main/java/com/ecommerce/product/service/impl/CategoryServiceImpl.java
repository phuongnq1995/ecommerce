package com.ecommerce.product.service.impl;

import com.ecommerce.product.dto.CategoryDTO;
import com.ecommerce.product.entity.Category;
import com.ecommerce.product.repository.CategoryRepository;
import com.ecommerce.product.service.CategoryService;
import com.ecommerce.product.validation.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

import static com.ecommerce.product.constant.CategoryConstant.CacheName;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryValidator categoryValidator;
	private final ModelMapper modelMapper;

	public CategoryServiceImpl(
		CategoryRepository categoryRepository, CategoryValidator categoryValidator, ModelMapper modelMapper) {

		this.categoryRepository = categoryRepository;
		this.categoryValidator = categoryValidator;
		this.modelMapper = modelMapper;
	}

	@Override
	@Cacheable(CacheName.CATEGORY_HIERARCHY)
	public List<Category> get() {

		return categoryRepository.findAll();
	}

	@Override
	@Cacheable(CacheName.CATEGORY_HIERARCHY)
	public List<? extends Category> getHierarchy() {

		// Separate root & sub categories groups, determine by key true or false
		Map<Boolean, List<CategoryDTO>> categoryGroup = get().stream()
			.map(category -> modelMapper.map(category, CategoryDTO.class))
			.collect(groupingBy(category -> Objects.isNull(category.getParentId())));

		// Map sub categories by their parentId
		Map<UUID, List<CategoryDTO>> subCategoryGroup = categoryGroup.get(false)
			.stream()
			.collect(groupingBy(CategoryDTO::getParentId));

		List<CategoryDTO> rootCategories = categoryGroup.get(true);

		// Set subCategories by parent Id
		rootCategories
			.stream()
			.collect(toMap(CategoryDTO::getId, Function.identity()))
			.forEach((rootId, rootCategory) -> rootCategory.setSubCategories(subCategoryGroup.get(rootId)));

		return rootCategories;
	}

	@Override
	public Optional<Category> get(UUID id) {

		return categoryRepository.findById(id);
	}

	@Override
	public Category create(Category category) {

		categoryValidator.validateCreateCategory(category);

		return categoryRepository.save(category);
	}

	@Override
	public Category update(UUID id, Category category) {

		categoryValidator.validateUpdateCategory(id, category);

		return categoryRepository.save(category);
	}

	@Override
	public void delete(UUID id) {

		categoryValidator.validateDeleteCategory(id);

		categoryRepository.deleteByParentId(id);

		categoryRepository.deleteById(id);
	}

}
