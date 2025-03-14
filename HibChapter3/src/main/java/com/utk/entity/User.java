package com.utk.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private LocalDate registrationDate;

	private String email;

	private int level;

	private boolean isActive;

	public User() {
	}

	public User(String username, LocalDate registrationDate) {
		this.username = username;
		this.registrationDate = registrationDate;
	}

	public User(String username, LocalDate registrationDate, String email, int level, boolean isActive) {
		this.username = username;
		this.registrationDate = registrationDate;
		this.email = email;
		this.level = level;
		this.isActive = isActive;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", registrationDate=" + registrationDate + ", email="
				+ email + ", level=" + level + ", isActive=" + isActive + "]";
	}

}
