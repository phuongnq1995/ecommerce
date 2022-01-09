package com.ecommerce.product.dto;

import com.ecommerce.product.entity.EcommerceImage;
import com.ecommerce.product.entity.Price;
import com.ecommerce.product.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO extends Product {

	private Set<EcommerceImage> images;
	private Set<Price> priceHistory;
}
