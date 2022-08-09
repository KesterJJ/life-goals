package com.qa.lifegoals.dtos;

import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	private Long id;

	private String name;

	private String description;

	private Goal goal;

	public TaskDTO(Task task) {
		this.id = task.getId();
		this.name = task.getName();
		this.description = task.getDescription();
		this.goal = task.getGoal();
	}

}
