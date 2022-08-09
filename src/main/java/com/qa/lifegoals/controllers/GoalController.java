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

	@PostMapping("/createGoal")
	public Goal create(@RequestBody Goal goal) {
		return service.addGoal(goal);
	}

	// READ
	@GetMapping("/getAllGoals")
	public List<GoalDTO> getAll() {
		return this.service.getAllGoals();
	}

	@GetMapping("/getOneGoal/{goalId}")
	public Goal getOne(@PathVariable("goalId") Long goalId) {
		return service.getOneGoal(goalId);
	}

	/*
	 * @GetMapping("/search") public List<Goal> search(@PathParam("search") String
	 * search) { if (service.searchByName("a").size() >= 1) { return
	 * 
	 * service.searchByName("a"); } else if (service.searchByDescription("a").size()
	 * >= 1) { return service.searchByName("a"); } else { return null; } }
	 */

	@PatchMapping("/updateGoal")
	public Goal update(@PathParam("goalId") Long goalId, @RequestBody Goal goal) {
		return service.updateGoal(goalId, goal);
	}

	@DeleteMapping("/deleteGoal")
	public boolean delete(@PathParam("goalId") Long goalId) {
		return service.removeGoal(goalId);
	}
}
