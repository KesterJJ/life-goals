package com.qa.lifegoals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.lifegoals.entities.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

	List<Task> findTaskByName(String name);

	List<Task> findTaskByDescription(String description);

	@Query(value = "SELECT id, name, description, goal_id FROM TASK WHERE goal_id = ?1", nativeQuery = true)
	List<Task> findAllByGoalId(Long goalId);

	@Query(value = "SELECT * FROM TASK WHERE name = 'Say Hello';", nativeQuery = true)
	List<Task> findTaskBySearchName(String search);

	@Query(value = "SELECT * FROM task WHERE name = 'Say Hello';", nativeQuery = true)
	List<Task> findTaskBySearchDescription(String search);

}
