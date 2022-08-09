package com.qa.lifegoals.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.lifegoals.entities.Task;
import com.qa.lifegoals.repositories.GoalRepo;
import com.qa.lifegoals.repositories.TaskRepo;

@Configuration
public class TaskConfig {

	@Autowired
	GoalRepo goalRepo;

	@Bean
	CommandLineRunner commandRunner(TaskRepo repo) {
		return args -> {
			Task sayHello = new Task("Say Hello", "Howdeedoodee", goalRepo.findById(2L).get());
			Task sayBye = new Task("Say Bye", "See ya", goalRepo.findById(1L).get());
			repo.saveAll(List.of(sayHello, sayBye));
		};

	}

}
