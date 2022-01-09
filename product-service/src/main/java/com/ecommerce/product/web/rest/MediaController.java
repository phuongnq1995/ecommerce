package com.ecommerce.product.web.rest;

import com.ecommerce.product.entity.EcommerceImage;
import com.ecommerce.product.service.ImageService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Media", description = "API of media")
@RequestMapping("/api/media")
public class MediaController {

	private final ImageService imageService;

	public MediaController(ImageService imageService) {
		this.imageService = imageService;
	}

	@PostMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Created the image",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EcommerceImage.class))})
	})
	public ResponseEntity<List<EcommerceImage>> createTempImages(@RequestParam("files") MultipartFile[] files) {

		List<EcommerceImage> ecommerceImages = imageService.saveEcommerceImages(files);

		return ResponseEntity.ok().body(ecommerceImages);
	}

}
