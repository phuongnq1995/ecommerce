package com.ecommerce.product.web.rest;

import com.ecommerce.product.documents.ProductDocument;
import com.ecommerce.product.dto.form.ProductForm;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.search.ProductSearch;
import com.ecommerce.product.service.ProductService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	private final ProductSearch productSearch;

	public ProductController(ProductService productService, ProductSearch productSearch) {
		this.productService = productService;
		this.productSearch = productSearch;
	}

	@GetMapping
	public ResponseEntity<SearchHits<ProductDocument>> all() {

		return ResponseEntity.ok(productSearch.search());
	}

	@GetMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Return the product",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDocument.class))}),
		@ApiResponse(responseCode = "404", description = "Invalid id supplied", content = @Content)
	})
	public ResponseEntity<ProductDocument> get(@PathVariable("id") UUID id) {

		return productSearch.get(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created the product",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content)
	})
	public ResponseEntity<Product> create(@RequestBody @Valid ProductForm product)
		throws URISyntaxException {

		Product result = productService.create(product);

		return ResponseEntity.created(new URI("/product/" + result.getId())).body(result);
	}

}
