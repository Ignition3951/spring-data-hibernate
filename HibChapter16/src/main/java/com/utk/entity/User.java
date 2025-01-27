package com.utk.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class User {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@Version
	private Long version;

	private String name;

	private boolean isRegistered;

	private boolean isCitizen;

	public User() {
	}

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public boolean isCitizen() {
		return isCitizen;
	}

	public void setCitizen(boolean isCitizen) {
		this.isCitizen = isCitizen;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", isRegistered=" + isRegistered + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isRegistered, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return isRegistered == other.isRegistered && Objects.equals(name, other.name);
	}

}
