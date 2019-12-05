package com.oreilly.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloControllers.class)
public class HelloRestControllerTest {
	
	@Test
	public void testSayHello() {
		HelloControllers controller = new HelloControllers();
		Model model = new BindingAwareModelMap();
		String result = controller.sayHello("world", model);
		assertEquals("hello", result);
		assertEquals("world", model.asMap().get("user"));
	}

}
