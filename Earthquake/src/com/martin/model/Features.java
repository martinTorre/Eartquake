package com.martin.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Features implements Serializable{
	
	private String type;
	private Properties properties;
	private Geometry geometry;
	private String id;
	private DetailProperties detailProperties;
	
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
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DetailProperties getDetailProperties() {
		return detailProperties;
	}
	public void setDetailProperties(DetailProperties detailProperties) {
		this.detailProperties = detailProperties;
	}
}
