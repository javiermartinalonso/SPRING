package com.oreilly.json;

import java.util.List;

public class Response {
	private List<Result> results;
	private String status;

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Location getLocation() {
		return results.get(0).getGeometry().getLocation();
	}
	
	public String getformattedAddress() {
		return results.get(0).getFormattedaddress();
	}
}
