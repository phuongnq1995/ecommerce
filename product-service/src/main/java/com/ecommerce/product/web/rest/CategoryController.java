package com.ecommerce.product.web.rest;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.feign.StoreClient;
import com.ecommerce.product.service.CategoryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.UUID;

@RestController
@Slf4j
@Tag(name = "Category", description = "API of categories")
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;
	private final StoreClient storeClient;

	public CategoryController(CategoryService categoryService, StoreClient storeClient) {
		this.categoryService = categoryService;
		this.storeClient = storeClient;
	}

	@GetMapping
	public ResponseEntity<Collection<? extends Category>> all() {

		return ResponseEntity.ok(categoryService.getHierarchy());
	}

	@GetMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Return the category",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
		@ApiResponse(responseCode = "404", description = "Invalid id supplied", content = @Content)
	})
	public ResponseEntity<Category> one(@PathVariable("id") UUID id) {

		return categoryService.get(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created the category",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content)
	})
	public ResponseEntity<Category> create(@RequestBody @Valid Category category)
		throws URISyntaxException {

		Category result = categoryService.create(category);

		return ResponseEntity.created(new URI("/category/" + result.getId()))
			.body(result);
	}

	@PutMapping("{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Updated the category",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
		@ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
	})
	public ResponseEntity<Category> update(
		@PathVariable(value = "id") final UUID id, @Valid @RequestBody Category category) {

		Category result = categoryService.update(id, category);

		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Deleted the category",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
		@ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
	})
	public ResponseEntity<Void> update(@PathVariable(value = "id") final UUID id) {

		categoryService.delete(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/limit")
	public ResponseEntity<Long> getLimit() {

		return ResponseEntity.ok(storeClient.getStoreProductLimit());
	}
}
