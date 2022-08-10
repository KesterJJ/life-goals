package com.qa.lifegoals.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.lifegoals.dtos.GoalDTO;
import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.repositories.GoalRepo;

@Service
public class GoalService {
	@Autowired
	private GoalRepo repo;

	@Autowired
	private ModelMapper mapper;

	private GoalDTO mapToDTO(Goal goal) {
		return mapper.map(goal, GoalDTO.class);
	}

	// CREATE
	public Goal addGoal(Goal goal) {
		return this.repo.save(goal);
	}

	// READ
	public List<GoalDTO> getAllGoals() {
		return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public Goal getOneGoal(Long id) {
		Optional<Goal> existenceCheckerOptional = this.repo.findById(id);
		Goal existingGoal = existenceCheckerOptional.orElseThrow();
		return existingGoal;
	}

	/*
	 * public List<Goal> searchByName(String name) { return
	 * repo.findGoalBySearchName("a"); }
	 * 
	 * public List<Goal> searchByDescription(String description) { return
	 * repo.findGoalBySearchDescription("a"); }
	 */

	// UPDATE
	public Goal updateGoal(Long id, Goal goal) {
		Optional<Goal> existenceCheckerOptional = this.repo.findById(id);
		Goal existingGoal = existenceCheckerOptional.orElse(new Goal());

		existingGoal.setGoalName(goal.getGoalName());
		existingGoal.setGoalDescription(goal.getGoalDescription());

		return this.repo.save(existingGoal);
	}

	// DELETE
	public boolean removeGoal(Long goalId) {
		repo.deleteById(goalId);
		boolean exists = this.repo.existsById(goalId);
		return !exists;
	}
}
