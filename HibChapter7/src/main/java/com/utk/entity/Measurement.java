package com.utk.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

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
