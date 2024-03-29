package com.todolist.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.dao.FolderRepository;
import com.todolist.dao.TaskRepository;
import com.todolist.model.Folder;
import com.todolist.model.Task;

@RestController
public class FolderController {

	private final FolderRepository folderRepository;
	private final TaskRepository taskRepository;

	FolderController(FolderRepository folderRepository, TaskRepository taskRepository) {
		this.folderRepository = folderRepository;
		this.taskRepository = taskRepository;
	}

	@GetMapping("/folder")
	public List<Folder> getAll() {
		List<Folder> folders = folderRepository.findAll();

		return folders;
	}

	@PostMapping("/folder")
	public void post(@RequestBody Folder newFolder) {
		Folder folder = new Folder();

		folder.setName(newFolder.getName());
		folderRepository.save(folder);
	}

	@PutMapping("/folder/{id}")
	public void putFolder(@RequestBody Folder updatedFolder, @PathVariable Long id) {
		Folder folder = folderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Could not find Folder with id: " + id));

		folder.setName(updatedFolder.getName());

		folderRepository.save(folder);
	}

@PutMapping("/folder/{id}/task")
	public void putTask(@RequestBody Task newTask, @PathVariable Long id) {
		Folder folder = folderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Could not find Folder with id: " + id));

		taskRepository.save(newTask);

		folder.addTask(newTask);

		folderRepository.save(folder);
	}

	@DeleteMapping("/folder/{id}")
	public void deleteFolder(@PathVariable Long id) {
		folderRepository.deleteById(id);
	}

	@DeleteMapping("/folder/{folderId}/task/{taskId}")
	public void deleteTask(@PathVariable Long folderId, @PathVariable Long taskId) {
		Folder folder = folderRepository.findById(folderId)
				.orElseThrow(() -> new RuntimeException("Could not find Folder with id: " + folderId));
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Could not find Task with id: " + taskId));

		folder.removeTask(task);

		taskRepository.deleteById(taskId);
	}
}
