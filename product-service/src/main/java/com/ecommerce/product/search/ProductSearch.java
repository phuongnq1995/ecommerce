package com.ecommerce.product.search;

import com.ecommerce.product.documents.ProductDocument;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.Optional;
import java.util.UUID;

public interface ProductSearch {

	void bulkIndex();

	SearchHits<ProductDocument> search();

	Optional<ProductDocument> get(UUID productId);
}
