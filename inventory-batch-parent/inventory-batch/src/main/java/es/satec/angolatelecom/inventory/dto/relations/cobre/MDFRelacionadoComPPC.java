package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.MDF;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParPrimarioDoCaboPPC;

public class MDFRelacionadoComPPC extends Relation {

	private static final long serialVersionUID = 1983387240333643749L;
	private static final String CLASSNAME = "MDFRelacionadoComPPC";
	
	public MDFRelacionadoComPPC() {
	}
	
	public MDFRelacionadoComPPC(MDF mdf, ParPrimarioDoCaboPPC parPrimarioDoCaboPPC) {
		super(CLASSNAME, (String) mdf.getProperty("nome"), (String) parPrimarioDoCaboPPC.getProperty("nome"));
	}

}
