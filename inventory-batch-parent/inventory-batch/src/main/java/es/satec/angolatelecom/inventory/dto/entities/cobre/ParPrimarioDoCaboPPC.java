package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.Par;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParPrimarioDoCaboPPC extends Par {

	private static final long serialVersionUID = -1435120042537043679L;
	private static final String CLASSNAME = "ParPrimarioDoCaboPPC";
	
	public ParPrimarioDoCaboPPC() {
		super();
	}
	
	public ParPrimarioDoCaboPPC(String name, EstadoPorta estado, Integer numero) {
		super(CLASSNAME, name, estado, numero);
	}

}
