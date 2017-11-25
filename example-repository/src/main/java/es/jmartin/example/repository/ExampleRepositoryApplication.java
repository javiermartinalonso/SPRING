package es.jmartin.example.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan({"es.jmartin.domain.model.dto"})
public class ExampleRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleRepositoryApplication.class, args);
	}
}
