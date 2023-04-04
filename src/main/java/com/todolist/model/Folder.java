package com.todolist.model;

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

	void addTask(Task task){
		tasks.add(task);
	}
}
