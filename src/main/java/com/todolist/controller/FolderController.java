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
import com.todolist.model.Folder;

@RestController
public class FolderController {

	private final FolderRepository folderRepository;

	FolderController(FolderRepository folderRepository) {
		this.folderRepository = folderRepository;
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
	public void putTaks(@RequestBody Folder updatedFolder, @PathVariable Long id) {
		Folder folder = folderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Could not find Folder with id: " + id));

		folder.setName(updatedFolder.getName());

		folderRepository.save(folder);
	}

	@DeleteMapping("/folder/{id}")
	public void deleteFolder(@PathVariable Long id) {
		folderRepository.deleteById(id);
	}
}
