package com.todolist.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Task {
	private @Id @GeneratedValue Long id;
	private String content;
	private boolean isDone;
	private Long parentTaskId;

	public Task() {}

	public Task(String content, boolean isDone, Long parentTaskId) {
		this.content = content;
		this.isDone = isDone;
		this.parentTaskId = parentTaskId;
	}

	// ### Gets and Setters ###

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public Long getParentTaskId() {
		return parentTaskId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	// ### Methods ###


	// ### Overrides ###
	// TODO: add parentTaskId

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Task))
			return false;

		Task task = (Task) o;

		return Objects.equals(this.id, task.id)
				&& Objects.equals(this.content, task.content)
				&& Objects.equals(this.isDone, task.isDone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.content, this.isDone);
	}

	@Override
	public String toString() {
		return "News {"
				+ "id=" + this.id
				+ ", content=" + this.content
				+ ", isDone=" + this.isDone
				+ "}";
	}
}
