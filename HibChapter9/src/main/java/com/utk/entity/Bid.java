package com.utk.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;

	private BigDecimal amount;

	public Bid() {
	}

	public Bid(Item item) {
		this.item = item;
	}

	public Bid(Item item, BigDecimal amount) {
		this.item = item;
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
