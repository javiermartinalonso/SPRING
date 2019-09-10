package es.satec.angolatelecom.inventory.dto.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.domain.entities.Resource;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Porto extends Resource {

	private static final long serialVersionUID = 7582035750871020746L;
	
	public Porto() {
	}
	
	public Porto(String className, String name, EstadoPorta estado) {
		super(className);
		super.addProperty("nome", name);
		super.addProperty("estado", estado.getValue());
	}

	/**
	 * @return the estado
	 */
	@JsonIgnore
	public String getEstado() {
		return (String) super.getProperty("estado");
	}
	
	@JsonIgnore
	public EstadoPorta getEstadoEnumerado() {
		return EstadoPorta.fromValue((String) super.getProperty("estado"));
	}

	/**
	 * @param estado the estado to set
	 */
	@JsonIgnore
	public void setEstado(EstadoPorta estado) {
		super.addProperty("estado", estado.getValue());
	}
	
	@JsonIgnore
	public void setEstado(String estado) {
		super.addProperty("estado", EstadoPorta.fromValue(estado).getValue());
	}

}
