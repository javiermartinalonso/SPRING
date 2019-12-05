package com.oreilly.json;

public class Result {
	//@JsonProperty("formatted_address")
	private String formattedaddress;
	private Geometry geometry;
	public String getFormattedaddress() {
		return formattedaddress;
	}
	public void setFormattedaddress(String formattedaddress) {
		this.formattedaddress = formattedaddress;
	}
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
}
