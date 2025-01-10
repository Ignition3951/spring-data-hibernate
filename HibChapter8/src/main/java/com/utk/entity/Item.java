package com.utk.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String name;

	@ElementCollection
	@CollectionTable(name = "IMAGES")
	@AttributeOverride(column = @Column(name = "FNAME", nullable = false), name = "filename")
	private Set<Image> images = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
	private Set<Bid> bids = new HashSet<>();

	public Item() {
	}

	public Item(Set<Image> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public Set<Image> getImages() {
		return Collections.unmodifiableSet(images);
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		images.add(image);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addBids(Bid bid) {
		bids.add(bid);
	}

//	@Override
//	public String toString() {
//		return "Item [id=" + id + ", images=" + images + "]";
//	}

}
