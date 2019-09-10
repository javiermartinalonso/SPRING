package es.satec.angolatelecom.inventory.dto.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceLocation {

	public static class LocationItem {
		private String name;
		private String code;
		private List<LocationItem> items;

		public LocationItem() {}
		
		public LocationItem(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public List<LocationItem> getItems() {
			return items;
		}

		public void setItems(List<LocationItem> items) {
			this.items = items;
		}

		@Override
		public String toString() {
			return "LocationItem [name=" + name + ", code=" + code + "]";
		}
		
		
	}

	private String code;
	private String name;
	private Double latitude;
	private Double longitude;
	private LocationItem country;
	private LocationItem province;
	private LocationItem region;
	private LocationItem town;
	private String address;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public LocationItem getCountry() {
		return country;
	}

	public void setCountry(LocationItem country) {
		this.country = country;
	}

	public LocationItem getProvince() {
		return province;
	}

	public void setProvince(LocationItem provincie) {
		this.province = provincie;
	}

	public LocationItem getRegion() {
		return region;
	}

	public void setRegion(LocationItem region) {
		this.region = region;
	}

	public LocationItem getTown() {
		return town;
	}

	public void setTown(LocationItem town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format(
				"ResourceLocation [code=%s, name=%s, latitude=%s, longitude=%s, country=%s, province=%s, region=%s, town=%s, address=%s]",
				code, name, latitude, longitude, country, province, region, town, address);
	}
}
