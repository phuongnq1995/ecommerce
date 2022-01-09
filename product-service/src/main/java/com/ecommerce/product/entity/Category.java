package com.ecommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category extends AbstractAuditingEntity {

	@NotNull(message = "must not be null")
	@Column(unique = true)
	private String name;

	@NotNull(message = "must not be null")
	@Column(unique = true)
	private String code;

	@Column(name = "parent_id")
	private UUID parentId;

	private String description;

}
