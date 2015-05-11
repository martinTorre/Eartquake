package com.martin.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Summary implements Serializable{

	private String type;
	private Metadata metadata;
	private Bbox bbox;
	private Features features [];
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public Bbox getBbox() {
		return bbox;
	}
	public void setBbox(Bbox bbox) {
		this.bbox = bbox;
	}
	public Features[] getFeatures() {
		return features;
	}
	public void setFeatures(Features[] features) {
		this.features = features;
	}
	
}