package com.qa.lifegoals.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Congig {

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

}
