package com.ecommerce.product.constant;

import lombok.Getter;

public class ApplicationConstants {

	@Getter
	public enum Folder {
		TEMP_PRODUCT;

		private final String name;

		Folder() {
			this.name = name().toLowerCase();
		}
	}
}
