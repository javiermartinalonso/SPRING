package es.jmartin.client.rest.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.jmartin.client.rest.dto.Quote;

@Service
public class ClientRestService {

	private static final Logger log = LoggerFactory.getLogger(ClientRestService.class);

	private final RestTemplate restTemplate;

	public ClientRestService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	// public Quote someRestCall(String name) {
	// return this.restTemplate.getForObject("/{name}/details", Quote.class, name);
	// }

	public Quote someRestCall(String name) {
		Quote quote = this.restTemplate.getForObject("/{name}/details", Quote.class);

		log.info(quote.toString());
		return quote;
	}

}