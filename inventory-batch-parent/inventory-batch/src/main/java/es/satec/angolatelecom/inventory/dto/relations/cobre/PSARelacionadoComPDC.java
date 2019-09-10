package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParDaCaixaPDC;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParSecundarioDoArmarioPSA;

public class PSARelacionadoComPDC extends Relation {

	private static final long serialVersionUID = -665597546906800542L;
	private static final String CLASSNAME = "PSARelacionadoComPDC";
	
	public PSARelacionadoComPDC() {
	}
	
	public PSARelacionadoComPDC(ParSecundarioDoArmarioPSA parSecundarioDoArmarioPSA, ParDaCaixaPDC parDaCaixaPDC) {
		super(CLASSNAME, (String) parSecundarioDoArmarioPSA.getProperty("nome"), (String) parDaCaixaPDC.getProperty("nome"));
	}

}
