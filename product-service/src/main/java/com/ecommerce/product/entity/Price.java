package com.ecommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price extends AbstractAuditingEntity {

	@Column
	private String note;

	@Column
	private boolean current;

	@NotNull(message = "must not be null")
	@DecimalMin(value = "0")
	@Column
	private BigDecimal price;

	@Column
	private UUID productId;
}
