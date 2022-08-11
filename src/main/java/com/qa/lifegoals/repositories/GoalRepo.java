package com.qa.lifegoals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.lifegoals.entities.Goal;

public interface GoalRepo extends JpaRepository<Goal, Long> {

	List<Goal> findGoalByGoalName(String goalName);

	List<Goal> findGoalByGoalDescription(String goalDescription);

	@Query(value = "SELECT * FROM GOAL WHERE end_user_id = ?1", nativeQuery = true)
	List<Goal> findAllByEndUserId(Long endUserId);

	@Query(value = "SELECT * FROM Goal WHERE name = 'Say Hello';", nativeQuery = true)
	List<Goal> findGoalBySearchName(String search);

	@Query(value = "SELECT * FROM Goal WHERE name = 'Say Hello';", nativeQuery = true)
	List<Goal> findGoalBySearchDescription(String search);

}
