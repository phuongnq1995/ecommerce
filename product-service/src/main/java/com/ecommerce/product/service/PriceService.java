package com.ecommerce.product.service;

import com.ecommerce.product.entity.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PriceService {

	void createPrice(UUID productId, BigDecimal price);

	List<Price> getProductPrices(UUID productId);
}
