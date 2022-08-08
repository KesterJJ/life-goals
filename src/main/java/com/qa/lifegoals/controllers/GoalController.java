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

import com.qa.lifegoals.dtos.GoalDTO;
import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.services.GoalService;

@RestController
public class GoalController {

	@Autowired
	private GoalService service;

	@PostMapping("/create")
	public Goal create(@RequestBody Goal goal) {
		return service.addGoal(goal);
	}

	// READ
	@GetMapping("/getAll")
	public List<GoalDTO> getAll() {
		return this.service.getAllGoals();
	}

	@GetMapping("/getOne/{id}")
	public Goal getOne(@PathVariable("id") Long id) {
		return service.getOneGoal(id);
	}

	/*
	 * @GetMapping("/search") public List<Goal> search(@PathParam("search") String
	 * search) { if (service.searchByName("a").size() >= 1) { return
	 * 
	 * service.searchByName("a"); } else if (service.searchByDescription("a").size()
	 * >= 1) { return service.searchByName("a"); } else { return null; } }
	 */

	@PatchMapping("/update")
	public Goal update(@PathParam("id") Long id, @RequestBody Goal goal) {
		return service.updateGoal(id, goal);
	}

	@DeleteMapping("/delete")
	public boolean delete(@PathParam("id") Long id) {
		return service.removeGoal(id);
	}
}
