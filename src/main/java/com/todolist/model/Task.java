package com.todolist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Task {
	private @Id @GeneratedValue Long id;
	private @ElementCollection(fetch = FetchType.EAGER) List<Task> children;
	private String content;
	private Boolean isDone;

	public Task(String content, boolean isDone) {
		this.content = content;
		this.isDone = isDone;
		this.children = new ArrayList<Task>();
	}

	public void addChildren(Task task) {
		this.children.add(task);
	}
}
