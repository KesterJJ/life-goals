package com.qa.lifegoals.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.lifegoals.entities.Task;
import com.qa.lifegoals.repositories.TaskRepo;

@Configuration
public class TaskConfig {

	@Bean
	CommandLineRunner commandLineRunner(TaskRepo repo) {
		return args -> {
			Task sayHello = new Task("Say Hello", "Howdeedoodee");
			Task sayBye = new Task("Say Bye", "See ya");
			repo.saveAll(List.of(sayHello, sayBye));
		};

	}
}
