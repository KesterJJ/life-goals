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

import com.qa.lifegoals.dtos.GoalDTO;
import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.services.GoalService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RestController
public class GoalController {

	@Autowired
	private GoalService service;

	@PostMapping("/createGoal")
	public Goal create(@RequestBody Goal goal) {
		return service.addGoal(goal);
	}

	// READ
	@GetMapping("/getAllGoals/{endUserId}")
	public List<GoalDTO> getAll(@PathVariable("endUserId") Long endUserId) {
		return this.service.getAllGoals(endUserId);
	}

	@PatchMapping("/updateGoal/{goalId}")
	public Goal update(@PathVariable("goalId") Long goalId, @RequestBody Goal goal) {
		return service.updateGoal(goalId, goal);
	}

	@DeleteMapping("/deleteGoal/{goalId}")
	public boolean delete(@PathVariable("goalId") Long goalId) {
		return service.removeGoal(goalId);
	}
}
