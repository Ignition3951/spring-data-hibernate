package com.utk.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.utk.entity.MonetaryAmount;

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
