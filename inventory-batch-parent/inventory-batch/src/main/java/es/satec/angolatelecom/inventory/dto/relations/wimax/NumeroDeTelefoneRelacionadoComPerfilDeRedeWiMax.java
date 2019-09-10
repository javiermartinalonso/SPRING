package es.satec.angolatelecom.inventory.dto.relations.wimax;

import es.satec.angolatelecom.inventory.domain.entities.Relation;
import es.satec.angolatelecom.inventory.dto.entities.common.NumeroDeTelefone;
import es.satec.angolatelecom.inventory.dto.entities.wimax.PerfilDeRedeWiMax;

public class NumeroDeTelefoneRelacionadoComPerfilDeRedeWiMax extends Relation {

	private static final long serialVersionUID = 7775501365236952693L;
	private static final String CLASSNAME = "NumeroDeTelefoneRelacionadoComPerfilDeRedeWiMax";
	
	public NumeroDeTelefoneRelacionadoComPerfilDeRedeWiMax() {
	}
	
	public NumeroDeTelefoneRelacionadoComPerfilDeRedeWiMax(NumeroDeTelefone numeroDeTelefone, PerfilDeRedeWiMax perfilDeRedeWiMax) {
		super(CLASSNAME, (String) numeroDeTelefone.getProperty("nome"), (String) perfilDeRedeWiMax.getProperty("nome"));
	}

}
