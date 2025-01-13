package com.utk.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Immutable;

import com.utk.constants.Constants;

@Entity
@Immutable
public class Bid {

	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Users bidder;

	@NotNull
	private BigDecimal amount;

	public Bid() {
	}

	public Bid(@NotNull Item item, @NotNull Users bidder, @NotNull BigDecimal amount) {
		this.item = item;
		this.bidder = bidder;
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Users getBidder() {
		return bidder;
	}

	public void setBidder(Users bidder) {
		this.bidder = bidder;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

}
