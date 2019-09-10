package es.satec.angolatelecom.inventory.dto.entities;

import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.domain.EstadoInventario;
import es.satec.angolatelecom.inventory.dto.entities.annotations.Id;
import es.satec.angolatelecom.inventory.dto.entities.annotations.ResourceType;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryResource {
	
	@Id
	private String nome;
	
	private String comentario;
	
	private String bookingTag;
	
	private String orientClass;
	
	private EstadoInventario estadoInventario = EstadoInventario.LIBRE;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResourceType() {
		
		ResourceType anno = AnnotationUtils.findAnnotation(getClass(), ResourceType.class);
		
		return anno != null ? anno.name() : orientClass;
	}
	
	public String getInventoryClass() {
		
		ResourceType anno = AnnotationUtils.findAnnotation(getClass(), ResourceType.class);
		
		return anno != null ? anno.inventoryClass() : orientClass;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getBookingTag() {
		return bookingTag;
	}

	public void setBookingTag(String bookingTag) {
		this.bookingTag = bookingTag;
	}

	public String getOrientClass() {
		return orientClass;
	}

	public void setOrientClass(String orientClass) {
		this.orientClass = orientClass;
	}

	public EstadoInventario getEstadoInventario() {
		return estadoInventario;
	}

	public void setEstadoInventario(EstadoInventario estadoInventario) {
		this.estadoInventario = estadoInventario;
	}

	@Override
	public String toString() {
		return "InventoryResource [nome=" + nome + ", comentario=" + comentario + "]";
	}
	
	
}
