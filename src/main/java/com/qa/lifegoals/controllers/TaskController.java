package com.qa.lifegoals.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.lifegoals.dtos.TaskDTO;
import com.qa.lifegoals.entities.Task;
import com.qa.lifegoals.services.TaskService;

@RestController
public class TaskController {

	Long id = 3L;

	Task task = new Task("yo", "bud");

	@Autowired
	private TaskService service;

	@GetMapping("/")
	public String home() {
		return "<h2>HI</h2>";
	}

	@PostMapping("/create")
	public Task create(@RequestBody Task task) {
		return service.addTask(task);
	}

	// READ
	@GetMapping("/getAll")
	public List<TaskDTO> getAll() {
		return this.service.getAllTasks();
	}

	@GetMapping("/getOne/{id}")
	public Task getOne(@PathVariable("id") Long id) {
		return service.getOneTask(id);
	}

	@GetMapping("/search/{search}")
	public List<Task> search(@PathParam("search") String search) {
		if (service.searchByName(search).size() >= 1) {
			return service.searchByName(search);
		}
		if (service.searchByDescription(search).size() >= 1) {
			return service.searchByName(search);
		} else {
			return null;
		}
	}

	@PatchMapping("/update")
	public Task update(@PathParam("id") Long i, @RequestBody Task tsk) {
		return service.updateTask(id, task);
	}

	@DeleteMapping("/delete")
	public boolean delete(@PathParam("id") Long i) {
		return service.removeTask(id);
	}

}
