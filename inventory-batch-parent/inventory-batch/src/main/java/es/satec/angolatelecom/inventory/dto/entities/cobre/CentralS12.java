package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.NetworkElement;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class CentralS12 extends NetworkElement {

	private static final long serialVersionUID = -2905025816826402666L;
	private static final String CLASSNAME = "CentralS12";

	public CentralS12() {
		super();
	}
	
	public CentralS12(String nombre, EstadoRecurso estado) {
		super(CLASSNAME, nombre, estado);
	}

}
