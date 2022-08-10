package com.qa.lifegoals.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	public Task(String taskName, String taskDescription, Goal goal) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.goal = goal;
	}

	public Task(String taskName, String taskDescription) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@Column(nullable = false, unique = true)
	private String taskName;

	@Column
	private String taskDescription;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "goal_id")
	private Goal goal;

}
