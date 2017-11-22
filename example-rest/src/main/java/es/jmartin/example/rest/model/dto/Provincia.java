package es.jmartin.example.rest.model.dto;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Persistent class for entity stored in table "provincia"
 */

@Entity
@Table(name="provincia", schema = "prototipo")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="Provincia.countAll", query="SELECT COUNT(x) FROM Provincia x" )
} )
public class Provincia  extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_Provincia", nullable=false)
    private Integer    idProvincia  ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nombre", nullable=false, length=250)
    private String     nombre       ;

	// "idpais" (column "idpais") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name="idpais", referencedColumnName="idpais")
    @JsonBackReference
    private Pais pais        ;

    @OneToMany(mappedBy="provincia", targetEntity=Municipio.class, fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SELECT)
    @JsonManagedReference
    private List<Municipio> listOfMunicipio;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Provincia() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdprovincia( Integer idprovincia ) {
        this.idProvincia = idprovincia ;
    }
    public Integer getIdprovincia() {
        return this.idProvincia;
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
    public void setPais( Pais pais ) {
        this.pais = pais;
    }
    public Pais getPais() {
        return this.pais;
    }

    public void setListOfMunicipio( List<Municipio> listOfMunicipio ) {
        this.listOfMunicipio = listOfMunicipio;
    }
    public List<Municipio> getListOfMunicipio() {
        return this.listOfMunicipio;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idProvincia);
        sb.append("]:"); 
        sb.append(nombre);
        return sb.toString(); 
    } 

}
