package com.ecommerce.product.documents;

import com.ecommerce.product.constant.SearchConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceDocument implements Serializable {

	private String note;

	private boolean current;

	private BigDecimal price;

	@Field(
		type = FieldType.Date,
		format = DateFormat.basic_date_time
	)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SearchConstants.BASIC_DATE_TIME_FORMAT_PATTERN)
	private Date lastModifiedDate;
}
