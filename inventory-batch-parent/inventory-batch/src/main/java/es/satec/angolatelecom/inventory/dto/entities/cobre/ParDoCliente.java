package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.Par;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParDoCliente extends Par {

	private static final long serialVersionUID = -7988113971144928755L;
	private static final String CLASSNAME = "ParDoCliente";
	
	public ParDoCliente() {
		super();
	}
	
	public ParDoCliente(String name, EstadoPorta estado, Integer numero) {
		super(CLASSNAME, name, estado, numero);
	}

}
