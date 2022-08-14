package com.qa.lifegoals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.lifegoals.entities.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

	@Query(value = "SELECT * FROM TASK WHERE goal_id = ?1", nativeQuery = true)
	List<Task> findAllByGoalId(Long goalId);

}
