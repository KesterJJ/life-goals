package com.qa.lifegoals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.lifegoals.entities.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

	List<Task> findTaskByName(String name);

	List<Task> findTaskByDescription(String description);
}
