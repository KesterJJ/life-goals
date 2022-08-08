package com.qa.lifegoals.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.lifegoals.entities.EndUser;
import com.qa.lifegoals.repositories.EndUserRepo;

@Configuration
public class EndUserConfig {
	@Bean
	CommandLineRunner commandLineRun(EndUserRepo repo) {
		return args -> {
			EndUser jimbob = new EndUser("Jimbobidy");
			EndUser jammy = new EndUser("Jambob");
			repo.saveAll(List.of(jimbob, jammy));
		};

	}
}
