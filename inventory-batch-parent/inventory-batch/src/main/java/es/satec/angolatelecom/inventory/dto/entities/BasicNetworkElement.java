package es.satec.angolatelecom.inventory.dto.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.domain.entities.Resource;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BasicNetworkElement extends Resource {

	private static final long serialVersionUID = -552065093413668907L;
	
	public BasicNetworkElement() {
	}
	
	public BasicNetworkElement(String className, String name, EstadoRecurso estado) {
		super(className);
		super.addProperty("nome", name);
		super.addProperty("estado", estado.getValue());
	}

	/**
	 * @return the tipo
	 */
	@JsonIgnore
	public String getTipo() {
		return (String) super.getProperty("tipo");
	}

	/**
	 * @param tipo the tipo to set
	 */
	@JsonIgnore
	public void setTipo(String tipo) {
		super.addProperty("tipo", tipo);
	}

	/**
	 * @return the modelo
	 */
	@JsonIgnore
	public String getModelo() {
		return (String) super.getProperty("modelo");
	}

	/**
	 * @param modelo the modelo to set
	 */
	@JsonIgnore
	public void setModelo(String modelo) {
		super.addProperty("modelo", modelo);
	}

	/**
	 * @return the fabricante
	 */
	@JsonIgnore
	public String getFabricante() {
		return (String) super.getProperty("fabricante");
	}

	/**
	 * @param fabricante the fabricante to set
	 */
	@JsonIgnore
	public void setFabricante(String fabricante) {
		super.addProperty("fabricante", fabricante);
	}

	/**
	 * @return the numeroSerie
	 */
	@JsonIgnore
	public String getNumeroSerie() {
		return (String) super.getProperty("numeroSerie");
	}

	/**
	 * @param numeroSerie the numeroSerie to set
	 */
	@JsonIgnore
	public void setNumeroSerie(String numeroSerie) {
		super.addProperty("numeroSerie", numeroSerie);
	}

	/**
	 * @return the eOL
	 */
	@JsonIgnore
	public Date getEol() {
		return (Date) super.getProperty("eol");
	}

	/**
	 * @param eOL the eOL to set
	 */
	@JsonIgnore
	public void setEol(Date eol) {
		super.addProperty("eol", eol);
	}

	/**
	 * @return the eOS
	 */
	@JsonIgnore
	public Date getEos() {
		return (Date) super.getProperty("eos");
	}

	/**
	 * @param eOS the eOS to set
	 */
	@JsonIgnore
	public void setEos(Date eos) {
		super.addProperty("eos", eos);
	}

	/**
	 * @return the oS
	 */
	@JsonIgnore
	public String getOs() {
		return (String) super.getProperty("os");
	}

	/**
	 * @param oS the oS to set
	 */
	@JsonIgnore
	public void setOs(String os) {
		super.addProperty("os", os);
	}

	/**
	 * @return the firmware
	 */
	@JsonIgnore
	public String getFirmware() {
		return (String) super.getProperty("firmware");
	}

	/**
	 * @param firmware the firmware to set
	 */
	@JsonIgnore
	public void setFirmware(String firmware) {
		super.addProperty("firmware", firmware);
	}

	@JsonIgnore
	public String getEstado() {
		return (String) super.getProperty("estado");
	}
	@JsonIgnore
	public EstadoRecurso getEstadoEnumerado() {
		return EstadoRecurso.fromValue((String) super.getProperty("estado"));
	}

	/**
	 * @param estado the estado to set
	 */
	@JsonIgnore
	public void setEstado(EstadoRecurso estado) {
		super.addProperty("estado", estado.getValue());
	}
	
	@JsonIgnore
	public void setEstado(String estado) {
		super.addProperty("estado", EstadoRecurso.fromValue(estado).getValue());
	}
	
}
