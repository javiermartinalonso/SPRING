package es.jmartin.domain.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Persistent class for entity stored in table "pais"
 */

@Entity
@Table(name="pais", schema = "prototipo")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="Pais.countAll", query="SELECT COUNT(x) FROM Pais x" )
} )
public class Pais extends ResourceSupport implements Serializable  {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idpais", nullable=false)
    private Integer    idpais       ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nombre", nullable=false, length=250)
    private String     nombre       ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="pais", targetEntity=Provincia.class, fetch=FetchType.LAZY)
//    @OneToMany(mappedBy="pais", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)  
    @JsonManagedReference
    private List<Provincia> listOfProvincia;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Pais() {
		super();
    }
    
    public Pais(Integer idpais, String nombre, List<Provincia> listOfProvincia) {
		super();
		this.idpais = idpais;
		this.nombre = nombre;
		this.listOfProvincia = listOfProvincia;
	}



	//----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdpais( Integer idpais ) {
        this.idpais = idpais ;
    }
    public Integer getIdpais() {
        return this.idpais;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : nombre ( varchar ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfProvincia( List<Provincia> listOfProvincia ) {
        this.listOfProvincia = listOfProvincia;
    }
    public List<Provincia> getListOfProvincia() {
        return this.listOfProvincia;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idpais);
        sb.append("]:"); 
        sb.append(nombre);
        return sb.toString(); 
    } 

}
