package com.martin.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Products implements Serializable{
	private Origin [] origin;

	public Origin[] getOrigin() {
		return origin;
	}

	public void setOrigin(Origin[] origin) {
		this.origin = origin;
	}
}
