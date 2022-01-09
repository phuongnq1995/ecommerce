package com.ecommerce.product.search.impl;

import com.ecommerce.product.constant.SearchConstants;
import com.ecommerce.product.documents.ImageDocument;
import com.ecommerce.product.documents.PriceDocument;
import com.ecommerce.product.documents.ProductDocument;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.search.ProductSearch;
import com.ecommerce.product.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ProductSearchImpl implements ProductSearch {

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
	private final ProductRepository productRepository;
	private final PriceService priceService;
	private final ModelMapper modelMapper;

	public ProductSearchImpl(
		ElasticsearchRestTemplate elasticsearchRestTemplate, ProductRepository productRepository,
		PriceService priceService, ModelMapper modelMapper) {

		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
		this.productRepository = productRepository;
		this.priceService = priceService;
		this.modelMapper = modelMapper;
	}

	public void bulkIndex() {

		List<IndexQuery> indexQueries = productRepository.findAll()
			.stream()
			.map(this::getIndexQuery)
			.collect(Collectors.toList());

		List<String> results = elasticsearchRestTemplate.bulkIndex(
			indexQueries, IndexCoordinates.of(SearchConstants.IndexName.PRODUCTS));

		log.info("Reindex:" + results.size() + " elements.");
	}

	@Transactional(readOnly = true)
	public Optional<ProductDocument> get(UUID productId) {

		return Optional.ofNullable(elasticsearchRestTemplate.get(
			productId.toString(), ProductDocument.class, IndexCoordinates.of(SearchConstants.IndexName.PRODUCTS)));
	}

	public SearchHits<ProductDocument> search() {

		Query query = new NativeSearchQueryBuilder()
			.addAggregation(
				AggregationBuilders.stats("category").field("category")
			)
			.build();

		return elasticsearchRestTemplate.search(
			query, ProductDocument.class, IndexCoordinates.of(SearchConstants.IndexName.PRODUCTS));
	}

	private IndexQuery getIndexQuery(Product product) {

		ProductDocument document = getProductDocument(product);

		return new IndexQueryBuilder()
			.withObject(document)
			.withId(product.getId().toString())
			.build();
	}

	private ProductDocument getProductDocument(Product source) {

		ProductDocument target = modelMapper.map(source, ProductDocument.class);

		// Set images
		List<ImageDocument> imageDocuments = source.getImages()
			.stream()
			.map(ecommerceImage -> modelMapper.map(ecommerceImage, ImageDocument.class))
			.collect(Collectors.toList());

		target.setImages(imageDocuments);

		// Set history prices
		List<PriceDocument> productPrices = priceService.getProductPrices(source.getId())
			.stream()
			.map(price -> modelMapper.map(price, PriceDocument.class))
			.collect(Collectors.toList());

		target.setHistoryPrices(productPrices);

		return target;
	}


}
