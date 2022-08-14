package com.qa.lifegoals.dtos;

import com.qa.lifegoals.entities.Goal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO {

	private Long goalId;

	private String goalName;

	private String goalDescription;

	public GoalDTO(Goal goal) {
		this.goalId = goal.getGoalId();
		this.goalName = goal.getGoalName();
		this.goalDescription = goal.getGoalDescription();
	}
}
