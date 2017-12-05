package es.jmartin.spring.cloud.function;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringCloudFunctionApplication {

	@Bean
	public Function<String, String> uppercase(){
		return value -> value.toUpperCase();
	}
	
	
	@Bean
	public Function<Flux<String>, Flux<String>> uppercaseflux(){
		return flux -> flux.filter(this::isNotRude).map(String::toUpperCase);
	}
	
	//Solo si la cadena contiene hola, la capitalizará
	boolean isNotRude(String word)
	{
		return word.toLowerCase().indexOf("hola")>-1?true:false;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFunctionApplication.class, args);
	}
}
