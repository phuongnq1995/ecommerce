package com.ecommerce.product.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cloud.aws")
public class AWSProperties {

	private String region;
	private Credentials credentials;

	@Data
	public static class Credentials {
		private String accessKey;
		private String secretKey;
	}
}
