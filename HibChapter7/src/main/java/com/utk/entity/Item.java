package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Item {

	@javax.persistence.Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long Id;

	private Dimensions dimensions;

	private Weight weight;

	public Item() {
	}

	public Item(Dimensions dimensions, Weight weight) {
		this.dimensions = dimensions;
		this.weight = weight;
	}

}
