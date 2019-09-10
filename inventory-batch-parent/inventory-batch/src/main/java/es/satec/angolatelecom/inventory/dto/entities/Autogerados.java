package es.satec.angolatelecom.inventory.dto.entities;

import es.satec.angolatelecom.inventory.domain.entities.Resource;

public abstract class Autogerados extends Resource {

	private static final long serialVersionUID = -8153497793178749838L;
	
	public Autogerados() {
	}
	
	public Autogerados(String className, String name) {
		super(className);
		super.addProperty("nome", name);
	}

}
