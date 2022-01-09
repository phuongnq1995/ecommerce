package com.ecommerce.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.configuration.Configuration;

@RestController
public class StoreController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/store/product/limit")
	public long getLimit() {

		return configuration.getLimit();
	}
}
