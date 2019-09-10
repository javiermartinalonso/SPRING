package es.satec.angolatelecom.inventory.dto.relations.cobre;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParPrimarioDoArmarioPPA;
import es.satec.angolatelecom.inventory.dto.entities.cobre.ParPrimarioDoCaboPPC;

public class PPCRelacionadoComPPA extends Relation {

	private static final long serialVersionUID = 1279973047133908218L;
	private static final String CLASSNAME = "PPCRelacionadoComPPA";
	
	public PPCRelacionadoComPPA() {
	}
	
	public PPCRelacionadoComPPA(ParPrimarioDoCaboPPC parPrimarioDoCaboPPC, ParPrimarioDoArmarioPPA parPrimarioDoArmarioPPA) {
		super(CLASSNAME, (String) parPrimarioDoCaboPPC.getProperty("nome"), (String) parPrimarioDoArmarioPPA.getProperty("nome"));
	}

}
