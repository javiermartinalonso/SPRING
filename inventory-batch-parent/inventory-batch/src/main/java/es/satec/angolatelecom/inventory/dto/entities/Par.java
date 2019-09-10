package es.satec.angolatelecom.inventory.dto.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.enumeration.EstadoPorta;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Par extends Porto {
	
	private static final long serialVersionUID = 1833702982407116940L;

	public Par() {
	}
	
	public Par(String className, String name, EstadoPorta estado, Integer numero) {
		super(className, name, estado);
		super.addProperty("numero", numero);
	}
	
	/**
	 * @return numero
	 */
	@JsonIgnore
	public Integer getNumero() {
		return (Integer) super.getProperty("numero");
	}

	/**
	 * @param numero
	 */
	@JsonIgnore
	public void setNumero(Integer numero) {
		super.addProperty("numero", numero);
	}

}
