package com.ecommerce.product.repository;

import com.ecommerce.product.entity.EcommerceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface EcommerceImageRepository extends JpaRepository<EcommerceImage, UUID> {

	boolean existsAllByIdIn(Collection<UUID> ids);

	List<EcommerceImage> getAllByIdIn(Collection<UUID> ids);
}
