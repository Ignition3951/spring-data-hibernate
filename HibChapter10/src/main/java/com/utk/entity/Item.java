package com.utk.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.utk.constants.Constants;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id;

	@NotNull
	@Size(min = 2, max = 255, message = "Please enter a string between 2 to 255 length")
	private String name;

	@Future
	private LocalDateTime auctionEnd;

	private BigDecimal buyNowPrice;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Users seller;

	@OneToMany(mappedBy = "item")
	private Set<Bid> bids = new HashSet<>();

	public Item() {
	}

	public Item(
			@NotNull @Size(min = 2, max = 255, message = "Please enter a string between 2 to 255 length") String name,
			@Future LocalDateTime auctionEnd, @NotNull Users seller) {
		this.name = name;
		this.auctionEnd = auctionEnd;
		this.seller = seller;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(LocalDateTime auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public BigDecimal getBuyNowPrice() {
		return buyNowPrice;
	}

	public void setBuyNowPrice(BigDecimal buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}

	public Long getId() {
		return id;
	}

	public Set<Bid> getBids() {
		return Collections.unmodifiableSet(bids);
	}

	public void addBids(Bid bid) {
		bids.add(bid);
	}

}
