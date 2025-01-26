package com.utk.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Bid {

	@Id
	@GeneratedValue(generator = "")
	private Long id;

	@NotNull
	private BigDecimal amount;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	public Bid() {
	}

	public Bid(@NotNull BigDecimal amount, Item item) {
		this.amount = amount;
		this.item = item;
		item.addBid(this);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

}
