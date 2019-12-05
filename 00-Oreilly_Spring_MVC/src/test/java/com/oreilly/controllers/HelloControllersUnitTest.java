package com.oreilly.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.oreilly.entities.Greeting;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloControllers.class)
public class HelloControllersUnitTest {
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void greetWithoutName() {
	    ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
	    assertEquals(HttpStatus.OK, entity.getStatusCode());
	    assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
	    Greeting response = entity.getBody();
	    assertEquals("Hello, World!", response.getGreeting());
	}

	@Test
	public void greetWithName() {
	    Greeting response = template.getForObject("/rest?name=Dolly", Greeting.class);
	    assertEquals("Hello, Dolly!", response.getGreeting());
	}

}
