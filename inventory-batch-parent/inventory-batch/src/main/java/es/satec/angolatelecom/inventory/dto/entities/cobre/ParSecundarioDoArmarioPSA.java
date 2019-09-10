package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.Par;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParSecundarioDoArmarioPSA extends Par {

	private static final long serialVersionUID = 5050714818666216521L;
	private static final String CLASSNAME = "ParSecundarioDoArmarioPSA";
	
	public ParSecundarioDoArmarioPSA() {
		super();
	}
	
	public ParSecundarioDoArmarioPSA(String name, EstadoPorta estado, Integer numero) {
		super(CLASSNAME, name, estado, numero);
	}

}
