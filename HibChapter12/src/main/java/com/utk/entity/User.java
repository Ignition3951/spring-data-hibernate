package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "USERS")
@BatchSize(size = 10)
public class User {

	private Long id;

	private String username;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	@NotNull
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
