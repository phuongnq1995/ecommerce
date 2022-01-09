package com.ecommerce.product.dto;

import java.util.List;

import com.ecommerce.product.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO extends Category {

	private List<CategoryDTO> subCategories;
}
