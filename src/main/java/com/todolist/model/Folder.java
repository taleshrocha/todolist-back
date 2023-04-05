package com.todolist.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Folder {

	private @Id @GeneratedValue Long id;
	private String name;
	@OneToMany
	private List<Task> tasks;

	public Folder(String name) {
		this.name = name;
		this.tasks = new ArrayList<>();
	}

	public void addTask(Task task){
		tasks.add(task);
	}

	public void removeTask(Task task){
		tasks.remove(task);
	}
}
