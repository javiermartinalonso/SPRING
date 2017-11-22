package es.jmartin.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories
public class ExampleRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleRestApplication.class, args);
	}
}
