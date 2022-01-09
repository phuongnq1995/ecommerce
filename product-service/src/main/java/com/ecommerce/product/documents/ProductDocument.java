package com.ecommerce.product.documents;

import com.ecommerce.product.constant.SearchConstants;
import com.ecommerce.product.entity.Price;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Document(indexName = SearchConstants.IndexName.PRODUCTS)
public class ProductDocument implements Serializable {

	@Id
	@Field(type = FieldType.Text)
	private UUID id;

	private String name;
	private String code;
	private String description;

	@Field(type = FieldType.Float)
	private BigDecimal price;

	@Field(type = FieldType.Object)
	private CategoryDocument category;

	@Field(type = FieldType.Nested)
	private List<ImageDocument> images;

	@Field(type = FieldType.Nested)
	private List<PriceDocument> historyPrices;

}
