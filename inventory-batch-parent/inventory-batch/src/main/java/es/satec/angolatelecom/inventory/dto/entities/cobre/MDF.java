package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.NetworkElement;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class MDF extends NetworkElement {
	
	private static final String CLASSNAME = "MDF";

	private static final long serialVersionUID = -2905025816826402666L;

	public MDF() {
		super();
	}
	public MDF(String nombre, EstadoRecurso estado) {
		super(CLASSNAME, nombre, estado);
	}

}
