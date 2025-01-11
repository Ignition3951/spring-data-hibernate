package com.utk.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "item")
	private Collection<Bid> bids = new ArrayList<>();

	@ManyToOne
	@JoinTable(name = "ITEM_BUYER", joinColumns = @JoinColumn(name = "ITEM_ID"), inverseJoinColumns = @JoinColumn(name = "BUYER_ID", nullable = false))
	private User buyer;

	@OneToMany(mappedBy = "item")
	private Set<CategorizedItem> categorizedItems = new HashSet<>();

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public Item(String name, Collection<Bid> bids) {
		this.name = name;
		this.bids = bids;
	}

	public Item(String name, User buyer) {
		this.name = name;
		this.buyer = buyer;
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

	public void addCategorizedItems(CategorizedItem categorizedItem) {
		categorizedItems.add(categorizedItem);
	}

}
