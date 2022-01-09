package com.ecommerce.product.documents;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ImageDocument implements Serializable {

	private UUID id;
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;

}
