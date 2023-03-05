package com.todolist.dao;

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
			taskRepository.save(new Task("Clean House", true));
			taskRepository.save(new Task("Study", true));
			taskRepository.save(new Task("Program", false));
			taskRepository.findAll().forEach(news -> log.info("PRELOADED:\n" + news ));

		};
	}
}
