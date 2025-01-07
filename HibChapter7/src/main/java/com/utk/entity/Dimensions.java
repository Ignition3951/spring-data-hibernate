package com.utk.entity;

import java.math.BigDecimal;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
@AttributeOverride(name = "name", column = @Column(name = "DIMENSIONS_NAME"))
@AttributeOverride(name = "symbol", column = @Column(name = "DIMENSIONS_SYMBOL"))
public class Dimensions extends Measurement {

	@NotNull
	private BigDecimal depth;

	@NotNull
	private BigDecimal height;

	@NotNull
	private BigDecimal width;

	public Dimensions() {
	}

	public Dimensions(@NotNull BigDecimal depth, @NotNull BigDecimal height, @NotNull BigDecimal width) {
		this.depth = depth;
		this.height = height;
		this.width = width;
	}

}
