package com.utk.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "CATEGORY_ITEM")
@Immutable
public class CategorizedItem {

	@Embeddable
	public static class Id implements Serializable {

		@Column(name = "CATEGORY_ID")
		private Long categoryId;

		@Column(name = "ITEM_ID")
		private Long itemId;

		public Id() {
		}

		public Id(Long categoryId, Long itemId) {
			this.categoryId = categoryId;
			this.itemId = itemId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(categoryId, itemId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			return Objects.equals(categoryId, other.categoryId) && itemId == other.itemId;
		}

	}

	@EmbeddedId
	private Id id = new Id();

	@Column(updatable = false)
	@NotNull
	private String addedBy;

	@Column(updatable = false)
	@NotNull
	@CreationTimestamp
	private LocalDateTime addedOn;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "CATEGORY_ID")
	private Category category;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "ITEM_ID")
	private Item item;

	public CategorizedItem(@NotNull String addedByUsername, Category category, Item item) {
		this.addedBy = addedByUsername;
		this.category = category;
		this.item = item;
		this.id.categoryId = category.getId();
		this.id.itemId = item.getId();
		category.addCategorizedItems(this);
		item.addCategorizedItems(this);
	}

	public CategorizedItem() {
	}

}
