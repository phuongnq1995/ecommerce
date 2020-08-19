package com.ecommerce.address.component.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	protected Date createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedAt;
}
