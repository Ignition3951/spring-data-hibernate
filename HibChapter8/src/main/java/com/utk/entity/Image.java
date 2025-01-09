package com.utk.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image {

	@Column(nullable = false)
	private String filename;

	private int width;

	private int height;

	public Image() {
	}

	public Image(String filename, int width, int height) {
		this.filename = filename;
		this.width = width;
		this.height = height;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int hashCode() {
		return Objects.hash(filename, height, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(filename, other.filename) && height == other.height && width == other.width;
	}

}
