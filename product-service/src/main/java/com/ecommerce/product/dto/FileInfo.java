package com.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileInfo {

	private String fileName;
	private String fileFolder;
	private String fileType;
	private long size;

}
