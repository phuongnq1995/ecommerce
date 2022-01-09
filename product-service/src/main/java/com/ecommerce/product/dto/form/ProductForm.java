package com.ecommerce.product.dto.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductForm implements Serializable {

	private UUID id;

	@NotNull(message = "must not be null")
	@Column(unique = true)
	private String name;

	@NotNull(message = "must not be null")
	@Column(unique = true)
	private String code;

	@Length(max = 2000)
	private String description;

	@NotNull(message = "must not be null")
	private UUID categoryId;

	@NotNull(message = "must not be null")
	@DecimalMin(value = "0")
	private BigDecimal price;

	private UUID[] imageIds;
}
