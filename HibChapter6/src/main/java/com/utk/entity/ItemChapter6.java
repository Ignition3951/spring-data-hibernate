package com.utk.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;

import com.utk.converter.MonetaryAmountConverter;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;

@Entity
public class ItemChapter6 extends Item {

	public ItemChapter6() {
	}

	@NotNull
	@Basic(fetch = FetchType.LAZY) // Defaults to EAGER
	private String description;

	@Formula("CONCAT(SUBSTR(DESCRIPTION, 1, 12), '...')")
	private String shortDescription;

	@Column(name = "IMPERIALWEIGHT")
	@ColumnTransformer(read = "IMPERIALWEIGHT / 2.20462", write = "? * 2.20462")
	private double metricWeight;

	@CreationTimestamp
	private LocalDate createdOn;

	@UpdateTimestamp
	private LocalDateTime lastModified;

	@Column(insertable = false)
	@ColumnDefault("1.00")
	@Generated(org.hibernate.annotations.GenerationTime.INSERT)
	private BigDecimal initialPrice;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AuctionType auctionType = AuctionType.HIGHEST_BID;

	@NotNull
	@Convert(converter = MonetaryAmountConverter.class)
	@Column(name = "PRICE", length = 63)
	private MonetaryAmount buyNowPrice;

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public double getMetricWeight() {
		return metricWeight;
	}

	public void setMetricWeight(double metricWeight) {
		this.metricWeight = metricWeight;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}

	public AuctionType getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(AuctionType auctionType) {
		this.auctionType = auctionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MonetaryAmount getBuyNowPrice() {
		return buyNowPrice;
	}

	public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}

}
