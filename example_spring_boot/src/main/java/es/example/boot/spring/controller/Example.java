package es.example.boot.spring.controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indica a spring que es un controlador rest
@RestController
//Indicamos a springboot que esta es la clase por la que debe arrancar
@EnableAutoConfiguration
public class Example {

    //Como controlador rest que es. Este metodo debe responder a esta url
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }

	//Metodo necesario para arrancar con Spring boot
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}