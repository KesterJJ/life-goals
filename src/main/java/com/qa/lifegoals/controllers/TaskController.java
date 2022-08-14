package com.qa.lifegoals.controllers;

import java.util.List;

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

	@PostMapping("/createTask")
	public Task create(@RequestBody Task task) {
		return service.addTask(task);
	}

	// READ
	@GetMapping("/getAllTasks/{goalId}")
	public List<TaskDTO> getAll(@PathVariable("goalId") Long goalId) {
		return this.service.getAllTasks(goalId);
	}

	@PatchMapping("/updateTask/{taskId}")
	public Task update(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
		return service.updateTask(taskId, task);
	}

	@DeleteMapping("/deleteTask/{taskId}")
	public boolean delete(@PathVariable("taskId") Long taskId) {
		return service.removeTask(taskId);
	}

}
