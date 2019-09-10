package es.satec.angolatelecom.inventory.dto.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NetworkElementCobertura extends NetworkElement {

	private static final long serialVersionUID = -3139872835714339282L;

	public NetworkElementCobertura() {
	}

	public NetworkElementCobertura(String className, String name, EstadoRecurso estado, Double latitude, Double longitude, Map<String, Float> cobertura) {
		super(className, name, estado);
		super.addProperty("latitude", latitude);
		super.addProperty("longitude", longitude);
		super.addProperty("cobertura", cobertura);
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
	
	/**
	 * @return the cobertura
	 */
	@SuppressWarnings("unchecked")
	@JsonIgnore
	public Map<String, Float> getCobertura() {
		return (Map<String, Float>) super.getProperty("cobertura");
	}

	/**
	 * @param cobertura the cobertura to set
	 */
	@JsonIgnore
	public void setCoberturas(Map<String, Float> cobertura) {
		super.addProperty("cobertura", cobertura);
	}

	/**
	 * @param cobertura the cobertura you are looking after
	 * @return the value of the cobertura 
	 */
	@SuppressWarnings("unchecked")
	@JsonIgnore
	public Float getCobertura(String cobertura) {
		return ((Map<String, Float>) super.getProperty("cobertura")).get(cobertura);
	}
	
	/**
	 * @param cobertura the name of the cobertura you are setting
	 * @param value the value of the cobertura you are setting
	 */
	@SuppressWarnings("unchecked")
	@JsonIgnore
	public void setCobertura(String cobertura, Float value) {
		((Map<String, Float>) super.getProperty("cobertura")).put(cobertura, value);
	}

}
