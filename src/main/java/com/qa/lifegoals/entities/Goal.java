package com.qa.lifegoals.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {

	public Goal(String goalName, String goalDescription) {
		this.goalName = goalName;
		this.goalDescription = goalDescription;
	}

	public Goal(String goalName, String goalDescription, EndUser endUser) {
		this.goalName = goalName;
		this.goalDescription = goalDescription;
		this.endUser = endUser;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goalId;

	@Column(nullable = false)
	private String goalName;

	@Column
	private String goalDescription;

	@ManyToOne
	@NotNull
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "end_user_id")
	private EndUser endUser;
}