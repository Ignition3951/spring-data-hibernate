package com.utk.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Measurement {

	@NotNull
	private String name;

	@NotNull
	private String symbol;

	public Measurement() {
		super();
	}

	public Measurement(@NotNull String name, @NotNull String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

}
