package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.Armario;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParPrimarioDoArmarioPPA;

public class ArmarioRelacionadoComPPA extends Relation {

	private static final long serialVersionUID = 2314109139067938532L;
	private static final String CLASSNAME = "ArmarioRelacionadoComPPA";
	
	public ArmarioRelacionadoComPPA() {
	}
	
	public ArmarioRelacionadoComPPA(Armario armario, ParPrimarioDoArmarioPPA parPrimarioDoArmarioPPA) {
		super(CLASSNAME, (String) armario.getProperty("nome"), (String) parPrimarioDoArmarioPPA.getProperty("nome"));
	}

}
