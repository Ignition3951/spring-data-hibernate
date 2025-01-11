package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Shipment {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@OneToOne
	@JoinTable(name = "ITEM_SHIPMENT", joinColumns = @JoinColumn(name = "SHIPMENT_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, unique = true))
	private Item auction;

	public Shipment() {
	}

	public Shipment(Item auction) {
		this.auction = auction;
	}

}
