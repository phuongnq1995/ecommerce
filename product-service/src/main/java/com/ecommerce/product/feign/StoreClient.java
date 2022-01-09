package com.ecommerce.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("store-service")
public interface StoreClient {

    @GetMapping("/store/product/limit")
    long getStoreProductLimit();
}
