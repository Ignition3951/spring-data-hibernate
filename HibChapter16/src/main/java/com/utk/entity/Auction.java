package com.utk.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Auction {

	private String auctionNumber;
	private int seats;
	private Set<User> users = new HashSet<>();

	public Auction(String auctionNumber, int seats) {
		this.auctionNumber = auctionNumber;
		this.seats = seats;
	}

	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Set<User> getUsers() {
		return Collections.unmodifiableSet(users);
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public boolean addUser(User user) {
		if (users.size() >= seats) {
			throw new RuntimeException("Cannot add more users than the capacity of the auction");
		}
		return users.add(user);
	}

	public boolean removeUser(User user) {
		return users.remove(user);
	}

	@Override
	public String toString() {
		return "Auction [auctionNumber=" + auctionNumber + "]";
	}

}
