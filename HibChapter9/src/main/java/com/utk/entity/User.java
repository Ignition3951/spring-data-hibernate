package com.utk.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String username;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
	@JoinColumn(unique = true)
	private Address address;

	@OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST)
	private Set<Item> boughtItems = new HashSet<>();

	public User() {
	}

	public User(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public User(String username, Address address, Set<Item> boughtItems) {
		this.username = username;
		this.address = address;
		this.boughtItems = boughtItems;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void addItems(Item item) {
		boughtItems.add(item);
	}

}
