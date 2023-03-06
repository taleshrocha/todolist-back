package com.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
			.orElseThrow(() -> new RuntimeException("Could not find Task with id: " + id));

		return task;
	}

	@PostMapping("/task")
	public Task newTask(@RequestBody Task newTask) {
		Task task = new Task(newTask.getContent(), newTask.getIsDone());
		taskRepository.save(task);

		return task;
	}

	@PutMapping("/task/{id}")
	public void putTaks(@RequestBody Task updatedTask, @PathVariable Long id) {
		Task task = taskRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Could not find Task with id: " + id));

		task.setContent(updatedTask.getContent());
		task.setIsDone(updatedTask.getIsDone());

		taskRepository.save(task);
	}

	@DeleteMapping("/task/{id}")
	public void deleteTask(@PathVariable Long id) {
		taskRepository.deleteById(id);
	}
}
