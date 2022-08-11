package com.qa.lifegoals.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.lifegoals.LifeGoalsApplication;
import com.qa.lifegoals.config.Config;
import com.qa.lifegoals.dtos.TaskDTO;
import com.qa.lifegoals.entities.Goal;
import com.qa.lifegoals.entities.Task;
import com.qa.lifegoals.repositories.TaskRepo;

@SpringBootTest(classes = { LifeGoalsApplication.class, Config.class })
@ActiveProfiles("test")
public class TaskServiceTest {

	@Autowired
	TaskService service;

	@MockBean
	TaskRepo repo;

	@MockBean
	Goal goal;

	// CREATE TEST
	@Test
	public void testAddTask() {
		Task task = new Task("Task4", "user2", goal);

		Mockito.when(repo.save(task)).thenReturn(task);

		Assertions.assertEquals(service.addTask(task), task);

		Mockito.verify(this.repo, Mockito.times(1)).save(task);
	}

	// READ TESTS

	@Test
	public void testGetAllTasks() {
		Goal goal1 = new Goal("goal1", "user1");
		Task task1 = new Task("task1", "goal1", goal1);

		Long goalId = 1L;

		List<Task> tasks = List.of(task1);

		List<TaskDTO> taskDTOs = List
				.of(new TaskDTO(task1.getTaskId(), task1.getTaskName(), task1.getTaskDescription()));

		Mockito.when(repo.findAllByGoalId(goalId)).thenReturn(tasks);

		Assertions.assertEquals(service.getAllTasks(goalId), taskDTOs);

		Mockito.verify(this.repo, Mockito.times(1)).findAllByGoalId(goalId);
		;
	}

	@Test
	public void testGetOneTask() {
		Goal goal1 = new Goal("goal1", "user1");

		Task expected = new Task(1L, "1ask1", "goal1", goal1);
		Long taskId = 1L;

		Mockito.when(repo.findById(taskId)).thenReturn(Optional.of(expected));

		Assertions.assertEquals(service.getOneTask(1L), expected);

		Mockito.verify(this.repo, Mockito.times(1)).findById(taskId);
	}

	// UPDATE TEST

	@Test
	public void testUpdateTask() {
		Long id = 1L;
		Goal goal1 = new Goal("goal1", "user1");
		Task expected = new Task(1L, "Task1", "user1", goal1);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(expected));
		Mockito.when(repo.save(expected)).thenReturn(expected);

		Assertions.assertEquals(service.updateTask(id, expected), expected);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(expected);
	}

	// DELETE TEST

	@Test
	public void testRemoveTask() {

		Long TaskId = 1L;
		boolean expected = true;

		// Mockito.when(repo.deleteById(TaskId)).thenReturn(1);

		Mockito.when(repo.existsById(TaskId)).thenReturn(false);

		Assertions.assertEquals(service.removeTask(TaskId), expected);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(TaskId);
	}
}
