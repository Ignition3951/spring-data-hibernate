package com.utk.entity;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AttributeOverride(name = "name", column = @Column(name = "WEIGHT_NAME"))
@AttributeOverride(name = "symbol", column = @Column(name = "WEIGHT_SYMBOL"))
public class Weight extends Measurement {

	@NotNull
	@Column(name = "WEIGHT")
	private BigDecimal value;

	public Weight() {
	}

	public Weight(@NotNull BigDecimal value) {
		this.value = value;
	}
}
