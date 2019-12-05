package com.oreilly.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oreilly.entries.Site;

/**
 * @author javier.martin
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class GeocodeServiceTest {

	private Logger logger = LoggerFactory.getLogger(GeocodeServiceTest.class);

	@Autowired
	private GeocodeService service;

	@Test
	public void getLatLngForGoogleHeadquarters() throws Exception {
		Site site = service.getLatLng("1600 Ampitheatre Parkway", "Mountain View", "CA");
		logger.info(site.toString());
		assertEquals(37.42, site.getLatitude(), 0.01);
		assertEquals(-122.08, site.getLongitude(), 0.01);
	}

	@Test
	public void getLatLngWithoutStreet() throws Exception {
		Site site = service.getLatLng("Boston", "MA");
		logger.info(site.toString());		
		assertEquals(42.36, site.getLatitude(), 0.01);
		assertEquals(-71.06, site.getLongitude(), 0.01);
	}
}
