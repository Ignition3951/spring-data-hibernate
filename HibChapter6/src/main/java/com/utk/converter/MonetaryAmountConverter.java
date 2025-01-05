package com.utk.converter;

import com.utk.entity.MonetaryAmount;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {

	@Override
	public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
		return monetaryAmount.toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String dbData) {
		return MonetaryAmount.fromString(dbData);
	}

}
