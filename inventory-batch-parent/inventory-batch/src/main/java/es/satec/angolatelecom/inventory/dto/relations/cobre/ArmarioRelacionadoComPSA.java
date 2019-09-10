package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.Armario;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParSecundarioDoArmarioPSA;

public class ArmarioRelacionadoComPSA extends Relation {

	private static final long serialVersionUID = 2314109139067938532L;
	private static final String CLASSNAME = "ArmarioRelacionadoComPSA";
	
	public ArmarioRelacionadoComPSA() {
	}
	
	public ArmarioRelacionadoComPSA(Armario armario, ParSecundarioDoArmarioPSA parSecundarioDoArmarioPSA) {
		super(CLASSNAME, (String) armario.getProperty("nome"), (String) parSecundarioDoArmarioPSA.getProperty("nome"));
	}

}
