package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.CentralS12;
import es.satec.angolatelecom.inventory.dto.entities.cobre.MDF;

public class CentralS12RelacionadoComMDF extends Relation  {

	private static final long serialVersionUID = 1324781662186309759L;
	private static final String CLASSNAME = "CentralS12RelacionadoComMDF";
	
	public CentralS12RelacionadoComMDF() {
	}
	
	public CentralS12RelacionadoComMDF(CentralS12 centralS12, MDF mdf) {
		super(CLASSNAME, (String) centralS12.getProperty("nome"), (String) mdf.getProperty("nome"));
	}

}
