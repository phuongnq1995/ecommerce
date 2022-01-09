package com.ecommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product extends AbstractAuditingEntity {

	@Column(unique = true)
	private String name;

	@Column(unique = true)
	private String code;

	private String description;

	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@NotNull(message = "must not be null")
	@DecimalMin(value = "0")
	private BigDecimal price;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "product_image",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "image_id")
	)
	private Collection<EcommerceImage> images = new ArrayList<>();
}
