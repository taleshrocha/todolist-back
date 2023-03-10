package com.todolist.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
public class Task {
	private @Id @GeneratedValue Long id;
	private String content;
	private Boolean isDone;
	private Long parentTaskId;

	public Task(String content, boolean isDone, Long parentTaskId) {
		this.content = content;
		this.isDone = isDone;
		this.parentTaskId = parentTaskId;
	}
}
