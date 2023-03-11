package com.todolist.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.todolist.model.Task;

@Configuration
class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(TaskRepository taskRepository) {

		return args -> {
			Task root = new Task("ROOT", true);
			Task clean = new Task("Clean House", false);
			Task floor = new Task("Clean floor", true);
			taskRepository.save(root);
			taskRepository.save(clean);
			taskRepository.save(floor);

			root.addChildren(clean);
			taskRepository.save(root);

			clean.addChildren(floor);
			taskRepository.save(clean);

			taskRepository.findAll().forEach(task -> log.info("PRELOADED:\n" + task));
		};
	}
}
