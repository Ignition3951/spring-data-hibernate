package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.utk.constants.Constants;

@Entity
@Table(name = "USERS")
public class Users {

	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id;

	private String username;

	private Address homeAddress;

	public Users() {
	}

	public Users(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Long getId() {
		return id;
	}

}
