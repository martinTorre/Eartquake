package com.martin.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Origin implements Serializable{
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
