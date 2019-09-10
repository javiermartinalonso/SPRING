package es.satec.angolatelecom.inventory.dto.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NetworkElement extends BasicNetworkElement {

	private static final long serialVersionUID = 4871840555255504088L;

	public NetworkElement() {
	}

	public NetworkElement(String className, String name, EstadoRecurso estado) {
		super(className, name, estado);
	}

	/**
	 * @return the ip
	 */
	@JsonIgnore
	public String getIp() {
		return (String) super.getProperty("ip");
	}

	/**
	 * @param ip the ip to set
	 */
	@JsonIgnore
	public void setIp(String ip) {
		super.addProperty("ip", ip);
	}
	
	/**
	 * @return the portoIp
	 */
	@JsonIgnore
	public Integer getPortoIp() {
		return (Integer) super.getProperty("portoIp");
	}

	/**
	 * @param portoIp the portoIp to set
	 */
	@JsonIgnore
	public void setPortoIp(Integer portoIp) {
		super.addProperty("portoIp", portoIp);
	}
	
	/**
	 * @return the portoIpGestao
	 */
	@JsonIgnore
	public Integer getPortoIpGestao() {
		return (Integer) super.getProperty("portoIpGestao");
	}

	/**
	 * @param portoIpGestao the portoIpGestao to set
	 */
	@JsonIgnore
	public void setPortoIpGestao(Integer portoIpGestao) {
		super.addProperty("portoIpGestao", portoIpGestao);
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

	/**
	 * @return the gestao
	 */
	@JsonIgnore
	public String getGestao() {
		return (String) super.getProperty("gestao");
	}

	/**
	 * @param gestao the gestao to set
	 */
	@JsonIgnore
	public void setGestao(String gestao) {
		super.addProperty("gestao", gestao);
	}

	/**
	 * @return the ipGestao
	 */
	@JsonIgnore
	public String getIpGestao() {
		return (String) super.getProperty("ipGestao");
	}

	/**
	 * @param ipGestao the ipGestao to set
	 */
	@JsonIgnore
	public void setIpGestao(String ipGestao) {
		super.addProperty("ipGestao", ipGestao);
	}

	/**
	 * @return the telnetUser
	 */
	@JsonIgnore
	public String getTelnetUser() {
		return (String) super.getProperty("telnetUser");
	}

	/**
	 * @param telnetUser the telnetUser to set
	 */
	@JsonIgnore
	public void setTelnetUser(String telnetUser) {
		super.addProperty("telnetUser", telnetUser);
	}

	/**
	 * @return the telnetPassword
	 */
	@JsonIgnore
	public String getTelnetPassword() {
		return (String) super.getProperty("telnetPassword");
	}

	/**
	 * @param telnetPassword the telnetPassword to set
	 */
	@JsonIgnore
	public void setTelnetPassword(String telnetPassword) {
		super.addProperty("telnetPassword", telnetPassword);
	}

	/**
	 * @return the monitorizacao
	 */
	@JsonIgnore
	public Boolean getMonitorizacao() {
		return (Boolean) super.getProperty("monitorizacao");
	}

	/**
	 * @param monitorizacao the monitorizacao to set
	 */
	@JsonIgnore
	public void setMonitorizacao(Boolean monitorizacao) {
		super.addProperty("monitorizacao", monitorizacao);
	}

	/**
	 * @return the snmpRead
	 */
	@JsonIgnore
	public String getSnmpRead() {
		return (String) super.getProperty("snmpRead");
	}

	/**
	 * @param snmpRead the snmpRead to set
	 */
	@JsonIgnore
	public void setSnmpRead(String snmpRead) {
		super.addProperty("snmpRead", snmpRead);
	}

	/**
	 * @return the snmpWrite
	 */
	@JsonIgnore
	public String getSnmpWrite() {
		return (String) super.getProperty("snmpWrite");
	}

	/**
	 * @param snmpWrite the snmpWrite to set
	 */
	@JsonIgnore
	public void setSnmpWrite(String snmpWrite) {
		super.addProperty("snmpWrite", snmpWrite);
	}

	/**
	 * @return the altura
	 */
	@JsonIgnore
	public Float getAltura() {
		return (Float) super.getProperty("altura");
	}

	/**
	 * @param altura the altura to set
	 */
	@JsonIgnore
	public void setAltura(Float altura) {
		super.addProperty("altura", altura);
	}

	/**
	 * @return the largura
	 */
	@JsonIgnore
	public Float getLargura() {
		return (Float) super.getProperty("largura");
	}

	/**
	 * @param largura the largura to set
	 */
	@JsonIgnore
	public void setLargura(Float largura) {
		super.addProperty("largura", largura);
	}

	/**
	 * @return the profundidade
	 */
	@JsonIgnore
	public Float getProfundidade() {
		return (Float) super.getProperty("profundidade");
	}

	/**
	 * @param profundidade the profundidade to set
	 */
	@JsonIgnore
	public void setProfundidade(Float profundidade) {
		super.addProperty("profundidade", profundidade);
	}

}
