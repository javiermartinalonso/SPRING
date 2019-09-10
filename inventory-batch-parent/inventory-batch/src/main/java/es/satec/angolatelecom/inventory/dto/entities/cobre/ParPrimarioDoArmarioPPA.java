package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.Par;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParPrimarioDoArmarioPPA extends Par {

	private static final long serialVersionUID = 6838955908091340684L;
	private static final String CLASSNAME = "ParPrimarioDoArmarioPPA";
	
	public ParPrimarioDoArmarioPPA() {
		super();
	}
	
	public ParPrimarioDoArmarioPPA(String name, EstadoPorta estado, Integer numero) {
		super(CLASSNAME, name, estado, numero);
	}

}
