package com.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.dao.TaskRepository;
import com.todolist.model.Task;

@RestController
public class TaskController {

	private final TaskRepository taskRepository;

	TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping("/task")
	public List<Task> all() {
		List<Task> tasks = taskRepository.findAll();

		return tasks;
	}

	@GetMapping("/task/{id}")
	public Task one(@PathVariable Long id) {
		Task task = taskRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Could not find Task with id:" + id));

		return task;
	}

	
}
