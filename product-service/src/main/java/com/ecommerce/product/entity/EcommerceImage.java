package com.ecommerce.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ecommerce_image")
public class EcommerceImage extends AbstractAuditingEntity {

	@Column(name = "file_name")
	private String fileName;
	@Column(name = "file_download_uri")
	private String fileDownloadUri;
	@Column(name = "file_type")
	private String fileType;
	private long size;

	public EcommerceImage(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
}
