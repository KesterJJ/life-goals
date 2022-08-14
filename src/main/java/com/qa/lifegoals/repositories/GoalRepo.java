package com.qa.lifegoals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.lifegoals.entities.Goal;

public interface GoalRepo extends JpaRepository<Goal, Long> {

	@Query(value = "SELECT * FROM GOAL WHERE end_user_id = ?1", nativeQuery = true)
	List<Goal> findAllByEndUserId(Long endUserId);

}
