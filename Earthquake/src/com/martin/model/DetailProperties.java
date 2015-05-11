package com.martin.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DetailProperties implements Serializable{

	private String type;
	private Properties properties;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
