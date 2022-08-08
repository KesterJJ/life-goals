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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RestController
public class TaskController {

	@Autowired
	private TaskService service;

	@GetMapping("/")
	public String home() {
		return "<h2>HI</h2>";
	}

	@PostMapping("/createTask")
	public Task create(@RequestBody Task task) {
		return service.addTask(task);
	}

	// READ
	@GetMapping("/getAllTasks")
	public List<TaskDTO> getAll() {
		return this.service.getAllTasks();
	}

	@GetMapping("/getOneTask/{id}")
	public Task getOne(@PathVariable("id") Long id) {
		return service.getOneTask(id);
	}

	/*
	 * @GetMapping("/search") public List<Task> search(@PathParam("search") String
	 * search) { if (service.searchByName("a").size() >= 1) { return
	 * 
	 * service.searchByName("a"); } else if (service.searchByDescription("a").size()
	 * >= 1) { return service.searchByName("a"); } else { return null; } }
	 */

	@PatchMapping("/updateTask")
	public Task update(@PathParam("id") Long id, @RequestBody Task task) {
		return service.updateTask(id, task);
	}

	@DeleteMapping("/deleteTask")
	public boolean delete(@PathParam("id") Long id) {
		return service.removeTask(id);
	}

}
