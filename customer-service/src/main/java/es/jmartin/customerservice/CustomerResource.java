package es.jmartin.customerservice;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





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
	
	private final Log log = LogFactory.getLog(getClass());
	
	@GET
	@Path("/{id}")
	public Customer byId (@PathParam("id") Long id, @Context SecurityContext context) {
		this.log.info(context.getUserPrincipal().getName() + " was here.");
		return customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Couldn`t find #" + id + "!"));
	}

}
