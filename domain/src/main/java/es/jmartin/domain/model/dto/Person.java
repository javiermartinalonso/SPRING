package es.jmartin.domain.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Persona", schema = "prototipo")
//Define named queries here
//@NamedQueries ( {
//@NamedQuery ( name="Persona.countAll", query="SELECT COUNT(x) FROM Person x" )
//} )
public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8466379786905403102L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="persona_hibernate_sequence")
//	@Column(name="id", nullable=false)
	private Integer id;

	@Column(name="nombre", nullable=false)
	private String nombre;
	@Column(name="apellidos", nullable=false)	
	private String apellidos;

	
	/*Necesario para que hibernate pueda crear la tabla 
	 * spring.jpa.hibernate.ddl-auto=update
	 * */
	public Person() {
//		super();
		// TODO Auto-generated constructor stub
	}

	
	public Person(String nombre, String apellidos) {
//		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}