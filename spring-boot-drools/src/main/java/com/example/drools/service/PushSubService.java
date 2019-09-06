package com.example.drools.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PushSubService {
	//@Autowired
	//private KafkaTemplate<String, String> kafkaTemplate;
	public void publishNewProductCreated(Object o) throws JsonProcessingException {
		String rawJSON = new ObjectMapper().writeValueAsString(o);
		//kafkaTemplate.send("newProduct", rawJSON); ...or whatelse
		System.out.println("Publishing newProduct Topic , content ["+rawJSON+"]");
	}
}
