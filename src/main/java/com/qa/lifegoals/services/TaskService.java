package com.qa.lifegoals.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.lifegoals.dtos.TaskDTO;
import com.qa.lifegoals.entities.Task;
import com.qa.lifegoals.repositories.TaskRepo;

@Service
public class TaskService {

	@Autowired
	private TaskRepo repo;

	@Autowired
	private ModelMapper mapper;

	private TaskDTO mapToDTO(Task task) {
		return mapper.map(task, TaskDTO.class);
	}

	// CREATE
	public Task addTask(Task task) {
		return this.repo.save(task);
	}

	// READ
	public List<TaskDTO> getAllTasks(Long goalId) {
		return repo.findAllByGoalId(goalId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// UPDATE
	public Task updateTask(Long id, Task task) {
		Optional<Task> existenceCheckerOptional = this.repo.findById(id);
		Task existingTask = existenceCheckerOptional.orElse(new Task());

		existingTask.setTaskName(task.getTaskName());
		existingTask.setTaskDescription(task.getTaskDescription());

		return this.repo.save(existingTask);
	}

	// DELETE
	public boolean removeTask(Long taskId) {
		repo.deleteById(taskId);
		boolean exists = this.repo.existsById(taskId);
		return !exists;
	}
}
