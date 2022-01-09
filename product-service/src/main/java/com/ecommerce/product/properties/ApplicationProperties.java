package com.ecommerce.product.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

	private String localStoragePath;
	private RemoteStorage remoteStorage;

	@Data
	public static class RemoteStorage {
		private String bucketName;
		private String publicURL;
	}
}
