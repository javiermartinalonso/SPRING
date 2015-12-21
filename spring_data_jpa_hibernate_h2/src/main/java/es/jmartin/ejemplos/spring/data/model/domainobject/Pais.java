package es.jmartin.ejemplos.spring.data.model.domainobject;

/**
 * INTERFACES DE JPA PARA RESOLVER LAS ANOTACIONES
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CLASE DE PERSISTENCIA PARA UNA TABLA DE MAESTROS DE PAISES
 */
@Entity
@Table(name = "pais", schema = "pruebas")
public class Pais 
//extends AbstractPersistable<Long>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="gen_pais_idpais_seq")
//	@SequenceGenerator(name="gen_pais_idpais_seq", sequenceName="gen_pais_idpais_seq", allocationSize=1)

	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "nacionalidad")
	private String nacionalidad;
	
	@Column(name = "idioma")
	private String idioma;

	public Pais(String nombre, String nacionalidad, String idioma)
	{
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.idioma = idioma;
	}

	public Pais(Long id) {
		super();
		this.setId(id);
	}
	
	public Pais() {
		super();
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getNacionalidad()
	{
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad)
	{
		this.nacionalidad = nacionalidad;
	}

	public String getIdioma()
	{
		return idioma;
	}

	public void setIdioma(String idioma)
	{
		this.idioma = idioma;
	}
}
