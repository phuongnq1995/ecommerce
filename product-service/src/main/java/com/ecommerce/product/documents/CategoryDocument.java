package com.ecommerce.product.documents;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CategoryDocument implements Serializable {

	private UUID id;
	private String name;
	private String code;
	private String description;
}
