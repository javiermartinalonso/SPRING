package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.Par;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParDaCaixaPDC extends Par {

	private static final long serialVersionUID = 608866645319982512L;
	private static final String CLASSNAME = "ParDaCaixaPDC";
	
	public ParDaCaixaPDC() {
		super();
	}
	
	public ParDaCaixaPDC(String name, EstadoPorta estado, Integer numero) {
		super(CLASSNAME, name, estado, numero);
	}

}
