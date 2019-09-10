package es.satec.angolatelecom.inventory.dto.entities.wimax;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.domain.entities.Resource;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerfilDeRedeWiMax extends Resource {

	private static final long serialVersionUID = 158545588115823939L;
	private static final String CLASSNAME = "PerfilDeRedeWiMax";
	
	public PerfilDeRedeWiMax() {
	}
	
	public PerfilDeRedeWiMax(String name, String pwdDados, String pwdVoz, String serialNumber, String usr, String mac) {
		super(CLASSNAME);
		super.addProperty("nome", name);
		super.addProperty("pwdDados", pwdDados);
		super.addProperty("pwdVoz", pwdVoz);
		super.addProperty("serialNumber", serialNumber);
		super.addProperty("usr", usr);
		super.addProperty("mac", mac);
	}

	/**
	 * @return the pwdDados
	 */
	@JsonIgnore
	public String getPwdDados() {
		return (String) super.getProperty("pwdDados");
	}

	/**
	 * @param pwdDados the pwdDados to set
	 */
	@JsonIgnore
	public void setPwdDados(String pwdDados) {
		super.addProperty("pwdDados", pwdDados);
	}

	/**
	 * @return the pwdVoz
	 */
	@JsonIgnore
	public String getPwdVoz() {
		return (String) super.getProperty("pwdVoz");
	}

	/**
	 * @param pwdVoz the pwdVoz to set
	 */
	@JsonIgnore
	public void setPwdVoz(String pwdVoz) {
		super.addProperty("pwdVoz", pwdVoz);
	}

	/**
	 * @return the serialNumber
	 */
	@JsonIgnore
	public String getSerialNumber() {
		return (String) super.getProperty("serialNumber");
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	@JsonIgnore
	public void setSerialNumber(String serialNumber) {
		super.addProperty("serialNumber", serialNumber);
	}

	/**
	 * @return the usr
	 */
	@JsonIgnore
	public String getUsr() {
		return (String) super.getProperty("usr");
	}

	/**
	 * @param usr the usr to set
	 */
	@JsonIgnore
	public void setUsr(String usr) {
		super.addProperty("usr", usr);
	}

	/**
	 * @return the mac
	 */
	@JsonIgnore
	public String getMac() {
		return (String) super.getProperty("mac");
	}

	/**
	 * @param mac the mac to set
	 */
	@JsonIgnore
	public void setMac(String mac) {
		super.addProperty("mac", mac);
	}

}
