package com.ecommerce.product.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ecommerce.product.properties.AWSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

	@Autowired
	private AWSProperties awsProperties;

	@Bean
	public AmazonS3 amazonS3() {

		AWSCredentials credentials = new BasicAWSCredentials(
			awsProperties.getCredentials().getAccessKey(), awsProperties.getCredentials().getSecretKey());

		return AmazonS3ClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(credentials))
			.withRegion(awsProperties.getRegion())
			.build();
	}

}
