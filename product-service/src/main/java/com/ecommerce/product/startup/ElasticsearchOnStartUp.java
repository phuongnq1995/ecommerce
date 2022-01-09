package com.ecommerce.product.startup;

import com.ecommerce.product.search.ProductSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ElasticsearchOnStartUp implements ApplicationRunner {

	private final ProductSearch productSearch;

	public ElasticsearchOnStartUp(ProductSearch productSearch) {
		this.productSearch = productSearch;
	}

	@Override
	public void run(ApplicationArguments args) throws InterruptedException {

		/*
		 * This delay should be replace by check health Elasticsearch service.
		 */
		log.info("Start delay of 15 seconds");

		TimeUnit.SECONDS.sleep(15);

		productSearch.bulkIndex();

		log.info("*****REINDEX****");
	}
}
