package com.utk.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Item {

	private Long id;

	private String name;

	private LocalDate auctionEnd;

	private User seller;

	private Set<Category> categories = new HashSet<>();

	private Set<Bid> bids = new HashSet<>();

	public Item() {
	}

	public Item(String name, LocalDate auctionEnd, User seller) {
		this.name = name;
		this.auctionEnd = auctionEnd;
		this.seller = seller;
	}

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public LocalDate getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(LocalDate auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@ManyToMany(mappedBy = "items")
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(mappedBy = "item")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public void addCategories(Category category) {
		categories.add(category);
	}

	public void addBids(Bid bid) {
		bids.add(bid);
	}

}
