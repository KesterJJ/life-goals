package com.qa.lifegoals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.lifegoals.entities.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
