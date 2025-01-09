package com.utk.entity;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	@Size(min = 2, max = 255, message = "Name is required, max of 255 characters")
	@Access(AccessType.PROPERTY)
	@Column(name = "ITEM_NAME")

	private String name;

	@Future
	protected LocalDate auctionDate;

	public Item() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = !name.startsWith("AUCTION: ") ? "AUCTION: " + name : name;
	}

	public LocalDate getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(LocalDate auctionDate) {
		this.auctionDate = auctionDate;
	}

	public Long getId() {
		return id;
	}

}
