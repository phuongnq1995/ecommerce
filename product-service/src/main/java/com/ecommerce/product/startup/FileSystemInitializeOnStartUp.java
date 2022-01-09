package com.ecommerce.product.startup;

import com.ecommerce.product.constant.ApplicationConstants;
import com.ecommerce.product.properties.ApplicationProperties;
import com.ecommerce.product.utils.EcommerceFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
@Component
public class FileSystemInitializeOnStartUp implements ApplicationRunner {

	private final ApplicationProperties applicationProperties;

	public FileSystemInitializeOnStartUp(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void run(ApplicationArguments args) {

		try {

			Path localStoragePath = Paths.get(applicationProperties.getLocalStoragePath());

			if (!Files.exists(localStoragePath)) {

				Files.createDirectories(localStoragePath);
			}

			Arrays.stream(ApplicationConstants.Folder.values())
				.forEach(folder -> EcommerceFileUtils.createSubFile(localStoragePath, folder.getName()));

		} catch (IOException ioException) {

			log.error("Cannot created root folders", ioException);
		}
	}
}
