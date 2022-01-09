package com.ecommerce.product.utils;

import com.ecommerce.product.web.errors.FileStorageException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@Slf4j
public class EcommerceFileUtils {

	public static Path createSubFile(Path path, String folder) {

		Path targetLocation = path.resolve(folder);

		if (!Files.exists(targetLocation)) {
			try {
				Files.createDirectory(targetLocation);
			} catch (IOException ex) {
				throw new FileStorageException("Could not create sub folder" + folder + ". Please try again!", ex);
			}
		}

		return targetLocation;
	}

	public static boolean deleteFolder(Path path) {

		try {

			Files.walk(path)
				.sorted(Comparator.reverseOrder())
				.map(Path::toFile)
				.forEach(File::delete);

			return true;

		} catch (IOException ioException) {

			log.error("Cannot delete folder {}", path.getFileName(), ioException);

			return false;
		}
	}

	public static String encodeURL(String url) {
		try {
			return URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error("Cannot encodeURL", e);
		}
		return url;
	}
}
