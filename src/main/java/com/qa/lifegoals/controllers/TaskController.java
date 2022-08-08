package com.qa.lifegoals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lifegoals.entities.Task;
import com.qa.lifegoals.repositories.TaskRepo;

@RestController
public class TaskController {

	@Autowired
	private TaskRepo repo;

	@GetMapping("/")
	public String home() {
		return "<h1>Hello World!</h1>";
	}

	@GetMapping("/getAll")
	public List<Task> getAll() {
		return repo.findAll();
	}
}
