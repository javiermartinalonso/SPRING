package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.CaixaDP;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParDaCaixaPDC;

public class DPRelacionadoComPDC extends Relation {

	private static final long serialVersionUID = -2904925409806018468L;
	private static final String CLASSNAME = "DPRelacionadoComPDC";
	
	public DPRelacionadoComPDC() {
	}
	
	public DPRelacionadoComPDC(CaixaDP caixaDP, ParDaCaixaPDC parDaCaixaPDC) {
		super(CLASSNAME, (String) caixaDP.getProperty("nome"), (String) parDaCaixaPDC.getProperty("nome"));
	}

}
