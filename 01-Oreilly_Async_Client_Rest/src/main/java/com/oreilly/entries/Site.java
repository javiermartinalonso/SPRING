package com.oreilly.entries;

public class Site {
	private Integer id;
	private String name;
	private double latitude;
	private double longitude;

	public Site() {
		super();
	}

	public Site(String name, double latitude, double longitude) {
		super();

		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
