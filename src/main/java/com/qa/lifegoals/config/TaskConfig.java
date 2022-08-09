package com.qa.lifegoals.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.qa.lifegoals.repositories.GoalRepo;

@Configuration
public class TaskConfig {

	@Autowired
	GoalRepo goalRepo;

	/*
	 * @Bean CommandLineRunner commandRunner(TaskRepo repo) { return args -> { Task
	 * sayHello = new Task("Say Hello", "Howdeedoodee",
	 * goalRepo.findById(2L).get()); Task sayBye = new Task("Say Bye", "See ya",
	 * goalRepo.findById(1L).get()); repo.saveAll(List.of(sayHello, sayBye)); };
	 * 
	 * }
	 */

}
