package com.utk.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}
