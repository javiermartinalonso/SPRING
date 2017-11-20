package es.jmartin.domain.model.dto;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "pais"
 */

@Entity
@Table(name="pais", schema="prototipo" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="Pais.countAll", query="SELECT COUNT(x) FROM Pais x" )
} )
public class Pais implements Serializable {

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
    @OneToMany(mappedBy="pais", targetEntity=Provincia.class)
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
