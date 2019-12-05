package com.oreilly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.oreilly.json.JokeResponse;

import reactor.core.publisher.Mono;

@Service
public class JokeService {
	private static final String BASE= "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
	
	
	private RestTemplate restTemplate;
	private WebClient client = WebClient.create("http://api.icndb.com");
	
	@Autowired
	public JokeService (RestTemplateBuilder builder)
	{
		restTemplate = builder.build();
	}
	
	public String getJoke(String first, String last)
	{
		String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
		
		JokeResponse response = restTemplate.getForObject(url, JokeResponse.class);
		String output = "";
		
		if (response!=null)
		{
			output = response.getValue().getJoke();
		}
		
		return output;
	}
	
	
	public Mono<String> getJokeAsync(String first, String last) {
	    String path = "/jokes/random?limitTo=[nerdy]&firstName={first}&lastName={last}";
	        return client.get()
	            .uri(path, first, last)
	            .retrieve()
	            .bodyToMono(JokeResponse.class)
	            .map(jokeResponse -> jokeResponse.getValue().getJoke());
	}

}
