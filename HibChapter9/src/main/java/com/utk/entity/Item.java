package com.utk.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "item")
	private Collection<Bid> bids = new ArrayList<>();

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public Item(String name, Collection<Bid> bids) {
		this.name = name;
		this.bids = bids;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addBids(Bid bid) {
		bids.add(bid);
	}

}
