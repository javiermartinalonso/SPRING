package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.CaixaDP;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParDoCliente;

public class DPRelacionadoComParDoCliente extends Relation {

	private static final long serialVersionUID = 6627587643908024081L;
	private static final String CLASSNAME = "DPRelacionadoComParDoCliente";
	
	public DPRelacionadoComParDoCliente() {
	}
	
	public DPRelacionadoComParDoCliente(CaixaDP caixaDP, ParDoCliente parDoCliente) {
		super(CLASSNAME, (String) caixaDP.getProperty("nome"), (String) parDoCliente.getProperty("nome"));
	}

}
