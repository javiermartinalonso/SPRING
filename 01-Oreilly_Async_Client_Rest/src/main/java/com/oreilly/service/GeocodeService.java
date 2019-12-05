package com.oreilly.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oreilly.entries.Site;
import com.oreilly.json.Response;

@Service
public class GeocodeService {
	private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
	//private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";
	private static final String KEY = "AIzaSyB6AUJjZXVxjB26fALTff_5FDMACq-ssRs";
//	private static final String KEY = "AIzaSyBscnGDyMtaK0KRvs-awIsgTA-L1e4jTbA";
	
	

	private RestTemplate restTemplate;

	@Autowired
	public GeocodeService(RestTemplateBuilder builder) {
		super();
		restTemplate = builder.build();
	}

	public Site getLatLng(String... address) {
		String joinedAddress = String.join(",", address);
		String encodeAddress = "";
		
		try {
			encodeAddress = URLEncoder.encode(joinedAddress, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Response response = restTemplate.getForObject(
				String.format("%s?address=%s&key=%s", BASE, encodeAddress, KEY), Response.class);
		return new Site(response.getformattedAddress(), 
				response.getLocation().getLat(),
				response.getLocation().getLng());
	}
}