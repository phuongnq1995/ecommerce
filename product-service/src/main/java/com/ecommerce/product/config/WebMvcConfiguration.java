package com.ecommerce.product.config;

import com.ecommerce.product.documents.PriceDocument;
import com.ecommerce.product.entity.AbstractAuditingEntity;
import com.ecommerce.product.entity.Price;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.*;

@Configuration
public class WebMvcConfiguration {

	private static final String[] HEADERS_EXPOSED = {
		ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_REQUEST_HEADERS, AUTHORIZATION,
		LOCATION
	};
	private static final Converter<Instant, Date> instantToDateConverter = new AbstractConverter<Instant, Date>() {
		@Override
		protected Date convert(Instant source) {
			return Objects.isNull(source) ? null : Date.from(source);
		}
	};

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").exposedHeaders(HEADERS_EXPOSED)
					.allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelmapper = new ModelMapper();

		TypeMap<Price, PriceDocument> priceToDocument = modelmapper.createTypeMap(Price.class, PriceDocument.class);
		priceToDocument.addMappings(mapping ->
			mapping.using(instantToDateConverter)
				.map(AbstractAuditingEntity::getLastModifiedDate, PriceDocument::setLastModifiedDate));

		return modelmapper;
	}


}
