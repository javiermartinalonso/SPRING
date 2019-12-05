/**
 * 
 */
package com.oreilly.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oreilly.service.JokeService;

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
		String joke = service.getJoke("craig", "walls");
		logger.info(joke);
		assertTrue(joke.contains("craig") || joke.contains("walls"));
	}

}
