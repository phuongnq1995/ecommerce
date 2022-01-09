package com.ecommerce.product.repository;

import com.ecommerce.product.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {

	List<Price> findByProductIdOrderByCreatedDateDesc(UUID productId);
}
