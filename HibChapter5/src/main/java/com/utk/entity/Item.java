package com.utk.entity;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

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
