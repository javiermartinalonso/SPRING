/**
 * 
 */
package com.oreilly.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.test.StepVerifier;

/**
 * @author javier.martin
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JokeServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(JokeServiceTest.class);
	
	@Autowired
	private JokeService service;
	
	

	/**
	 * Test method for {@link com.oreilly.service.JokeService#getJoke(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetJoke() {
		String joke = service.getJoke("Craig", "Walls");
		logger.info(joke);
		assertTrue(joke.contains("Craig") || joke.contains("Walls"));
	}

	
	@Test
	public void testGetJokeAsync() {
		String joke = service.getJokeAsync("Craig", "Walls")
				.block(Duration.ofSeconds(2));
		logger.info(joke);
		assertTrue(joke.contains("Craig") || joke.contains("Walls"));
	}
	
	
	@Test
	public void usestepVerifier() {
		StepVerifier.create(service.getJokeAsync("Craig", "Walls"))
					.assertNext(joke -> {
						logger.info(joke);
						assertTrue(joke.contains("Craig") || joke.contains("Walls"));
					})
					.verifyComplete();
	}	
	
}
