/**
 * 
 */
package com.raidentrance.crypto.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

/**
 * @author raidentrance
 *
 */
public class AvailableBooksTest {
	@Test
	public void testGetAvailableBooks() {
		get("https://api.bitso.com/v3/available_books/").then().body("success", equalTo(true));
	}

	@Test
	public void getTickerByBook() {
		given().param("book", "xrp_mxn").get("https://api.bitso.com/v3/ticker").then().statusCode(200);
	}

	@Test
	public void validateGetTickerPrice() {
		given().param("book", "xrp_mxn").get("https://api.bitso.com/v3/ticker").then().statusCode(200).and()
				.body("payload.ask", notNullValue());
	}
}
