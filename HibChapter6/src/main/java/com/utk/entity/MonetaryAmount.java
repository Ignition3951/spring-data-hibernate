package com.utk.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MonetaryAmount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final BigDecimal value;

	private final Currency currency;

	public MonetaryAmount(BigDecimal value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null || getClass() != obj.getClass())
			return false;
		MonetaryAmount monetaryAmount = (MonetaryAmount) obj;
		return Objects.equals(value, monetaryAmount.value) && Objects.equals(currency, monetaryAmount.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, currency);
	}

	@Override
	public String toString() {
		return value + " " + currency;
	}

	public static MonetaryAmount fromString(String s) {
		String[] split = s.split(" ");
		return new MonetaryAmount(new BigDecimal(split[0]), Currency.getInstance(split[1]));
	}

}
