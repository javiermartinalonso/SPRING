package es.jmartin.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Anotaciones de lombok para "condimentar" los pojos con menos codigo.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	

	
	
	
}
