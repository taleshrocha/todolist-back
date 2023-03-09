package com.todolist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByParentTaskId(Long parentTaskId);
}
