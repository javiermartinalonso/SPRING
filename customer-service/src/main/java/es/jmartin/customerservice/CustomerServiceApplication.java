package es.jmartin.customerservice;

import java.util.stream.Stream;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class CustomerServiceApplication {

	
	@Bean
	ApplicationRunner data(CustomerRepository cr) {
		return args -> 
			Stream.of("A", "B", "C")
//				.forEach(x -> cr.save(new Customer(id: null, x)));
				.forEach(x -> cr.save(new Customer(null, x)));
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}	

@Component
class GenericExceptionMapper implements ExceptionMapper<IllegalArgumentException>{
	
	@Override
	public Response toResponse(IllegalArgumentException exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	}
}
	
@Configuration
class JerseyConfiguration {
	
	@Bean
	CustomerResource customerResource(CustomerRepository customerRepository) {
		return new CustomerResource(customerRepository);
	}
	
	
	@Bean
	ResourceConfig config(CustomerResource cr, GenericExceptionMapper exceptionMapper) {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.register(exceptionMapper);
		resourceConfig.register(cr);
		return resourceConfig;
	}
}

