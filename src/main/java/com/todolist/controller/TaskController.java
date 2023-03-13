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
	public Task getRoot() {
		Task task = taskRepository.findById(1L)
			.orElseThrow(() -> new RuntimeException("Could not Find Root."));

		return task;
	}

	@PostMapping("/task/{parentId}") 
	public void postTask(@RequestBody Task newTask, @PathVariable Long parentId){
		Task task = new Task();

		Task parentTask = taskRepository.findById(parentId)
			.orElseThrow(() -> new RuntimeException("Could not Find Task With Id: " + parentId));

		task.setContent(newTask.getContent());
		task.setIsDone(newTask.getIsDone());
		taskRepository.save(task);

		parentTask.addChildren(task);
		taskRepository.save(parentTask);
	}

	@PutMapping("/task/{id}")
	public void putTaks(@RequestBody Task updatedTask, @PathVariable Long id) {
		Task task = taskRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Could not find Task with id: " + id));

		task.setContent(updatedTask.getContent());
		task.setIsDone(updatedTask.getIsDone());

		taskRepository.save(task);
	}

	// TODO: delete all children
	@DeleteMapping("/task/{id}")
	public void deleteTask(@PathVariable Long id) {
		taskRepository.deleteById(id);
	}
}
