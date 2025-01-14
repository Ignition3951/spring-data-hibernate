package com.utk.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.utk.constants.Constants;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id;

	@Version
	private long version;

	@NotNull
	private String name;

	private LocalDate creationDate;

	private BigDecimal buyNowPrice;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public Item(String name, LocalDate creationDate) {
		this.name = name;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBuyNowPrice() {
		return buyNowPrice;
	}

	public void setBuyNowPrice(BigDecimal buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}

	@Override
	public String toString() {
		return "Item{" + "name='" + name + '\'' + ", creationDate=" + creationDate + '}';
	}
}