package com.ecommerce.product.service.impl;

import com.ecommerce.product.entity.Price;
import com.ecommerce.product.repository.PriceRepository;
import com.ecommerce.product.service.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PriceServiceImpl implements PriceService {

	private final PriceRepository priceRepository;

	public PriceServiceImpl(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void createPrice(UUID productId, BigDecimal price) {

		List<Price> oldPrices = priceRepository.findByProductIdOrderByCreatedDateDesc(productId);

		oldPrices.forEach(oldPrice -> oldPrice.setCurrent(false));

		Price currentPrice = Price.builder()
			.productId(productId)
			.price(price)
			.current(true)
			.build();

		oldPrices.add(currentPrice);

		priceRepository.saveAll(oldPrices);
	}

	@Transactional(readOnly = true)
	public List<Price> getProductPrices(UUID productId) {

		return priceRepository.findByProductIdOrderByCreatedDateDesc(productId);
	}
}
