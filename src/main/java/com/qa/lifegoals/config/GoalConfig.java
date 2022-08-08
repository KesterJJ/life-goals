package com.qa.lifegoals.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.repositories.GoalRepo;

@Configuration
public class GoalConfig {

	@Bean
	CommandLineRunner commandLineRunner(GoalRepo repo) {
		return args -> {
			Goal sayWuddup = new Goal("Say Wuddup", "yoooooooo");
			Goal sayCiao = new Goal("Say Ciao", "Adios");
			repo.saveAll(List.of(sayWuddup, sayCiao));
		};

	}
}
