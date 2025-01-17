package com.utk.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Bid {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private User bidder;

	@NotNull
	private BigDecimal amount;

	public Bid() {
	}

	public Bid(@NotNull Item item, @NotNull User bidder, @NotNull BigDecimal amount) {
		this.item = item;
		this.bidder = bidder;
		this.amount = amount;
	}

	public Bid(@NotNull BigDecimal amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
