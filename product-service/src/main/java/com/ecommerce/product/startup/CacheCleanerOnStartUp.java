package com.ecommerce.product.startup;

import com.ecommerce.product.constant.CategoryConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Component
public class CacheCleanerOnStartUp implements ApplicationRunner {

	private final RedisTemplate<String, Object> redisTemplate;

	public CacheCleanerOnStartUp(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void run(ApplicationArguments args) {

		Stream.of(CategoryConstant.CacheName.cacheNames())
			.forEach(redisTemplate::delete);

		log.info("Cleaned up data in redis cache.");
	}
}
