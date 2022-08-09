package com.qa.lifegoals.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goalId;

	@Column(nullable = false, unique = true)
	private String goalName;

	@Column
	private String goalDescription;

	@OneToMany(mappedBy = "goal")
	private Set<Task> tasks = new HashSet<>();

}