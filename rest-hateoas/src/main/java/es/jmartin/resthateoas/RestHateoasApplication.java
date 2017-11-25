package es.jmartin.resthateoas;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.jmartin.domain.model.dto.Person;
import es.jmartin.example.repository.domain.PersonaRepository;

//https://stackoverflow.com/questions/34367316/spring-boot-autowired-does-not-work-classes-in-different-package
//@SpringBootApplication(scanBasePackages = "es.jmartin")
@SpringBootApplication(scanBasePackages = {"es.jmartin.domain", "es.jmartin.example.repository", "es.jmartin.resthateoas"})
//@EnableJpaRepositories
//@ComponentScan({"es.jmartin.domain.model.*", "es.jmartin.resthateoas.rest"})
public class RestHateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestHateoasApplication.class, args);
	}
		
	@Bean
	CommandLineRunner runner(PersonaRepository personRepository){
	    return  x ->{
	        Arrays.asList(
	                new Person("Javier", "Martín Alonso") ,
	                new Person("otro", "Martín Alonso")
	        ).forEach(personRepository::save);

//	        log.info("Carga inicial en BD -  Hecha.");
	    };
	}	
}
