package com.qa.lifegoals.dtos;

import com.qa.lifegoals.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	private Long taskId;

	private String taskName;

	private String taskDescription;

	public TaskDTO(Task task) {
		this.taskId = task.getTaskId();
		this.taskName = task.getTaskName();
		this.taskDescription = task.getTaskDescription();
	}

}
