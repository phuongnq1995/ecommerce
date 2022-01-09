package com.ecommerce.product.constant;

public class CategoryConstant {

	public interface CacheName {
		String CATEGORIES = "categories";
		String CATEGORY_HIERARCHY = "category_hierarchy";

		static String[] cacheNames() {
			return new String[]{
				CATEGORIES, CATEGORY_HIERARCHY
			};
		}
	}
}
