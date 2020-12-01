package com.finadv.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author atanu
 *
 */
@Configuration
public class BeanConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.build();
	}
}
