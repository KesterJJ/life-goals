package com.qa.lifegoals.dtos;

import com.qa.lifegoals.entities.Goal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO {

	private Long id;

	private String name;

	private String description;

	public GoalDTO(Goal goal) {
		this.id = goal.getGoalId();
		this.name = goal.getName();
		this.description = goal.getDescription();
	}
}
