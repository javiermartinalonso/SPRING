package es.jmartin.customerservice;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	private final CustomerRepository customerRepository;
	
	//Revisar esto, se podría autoinyectar con anotaciones y dejarse de mierdas
	public CustomerResource(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;	
	}
	
	//Al no poner path es el de la clase osea "/customers"
	@GET
	public Collection <Customer> customers(){
		return this.customerRepository.findAll();
	}
	
	@GET
	@Path("/{id}")
	public Customer byId (@PathParam("id") Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Couldn`t find #" + id + "!"));
	}

}
