package es.satec.angolatelecom.inventory.dto.entities.cobre;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.BasicNetworkElement;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Armario extends BasicNetworkElement {

	private static final long serialVersionUID = -5033386082809412744L;
	private static final String CLASSNAME = "Armario";
	
	public Armario() {
		super();
	}
	
	public Armario(String name, EstadoRecurso estado) {
		super(CLASSNAME, name, estado);
	}
	
	/**
	 * @return the latitude
	 */
	@JsonIgnore
	public Double getLatitude() {
		return (Double) super.getProperty("latitude");
	}

	/**
	 * @param latitude the latitude to set
	 */
	@JsonIgnore
	public void setLatitude(Double latitude) {
		super.addProperty("latitude", latitude);
	}

	/**
	 * @return the longitude
	 */
	@JsonIgnore
	public Double getLongitude() {
		return (Double) super.getProperty("longitude");
	}

	/**
	 * @param longitude the longitude to set
	 */
	@JsonIgnore
	public void setLongitude(Double longitude) {
		super.addProperty("longitude", longitude);
	}

}
