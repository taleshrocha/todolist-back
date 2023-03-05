package com.todolist.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
