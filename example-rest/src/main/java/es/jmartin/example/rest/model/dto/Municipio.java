package es.jmartin.example.rest.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Persistent class for entity stored in table "municipio"
 */

@Entity
@Table(name="municipio", schema = "prototipo")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="Municipio.countAll", query="SELECT COUNT(x) FROM Municipio x" )
} )
public class Municipio  extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idmunicipio", nullable=false)
    private Integer    idmunicipio  ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nombre", nullable=false, length=250)
    private String     nombre       ;

	// "idprovincia" (column "idProvincia") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SELECT)    
    @JoinColumn(name="id_Provincia", referencedColumnName="id_Provincia")
    @JsonBackReference
    private Provincia provincia   ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Municipio() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdmunicipio( Integer idmunicipio ) {
        this.idmunicipio = idmunicipio ;
    }
    public Integer getIdmunicipio() {
        return this.idmunicipio;
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
    public void setProvincia( Provincia provincia ) {
        this.provincia = provincia;
    }
    public Provincia getProvincia() {
        return this.provincia;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idmunicipio);
        sb.append("]:"); 
        sb.append(nombre);
        return sb.toString(); 
    } 

}
