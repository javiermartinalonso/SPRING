package es.satec.angolatelecom.inventory.dto.wimax;

public class WimaxResultDto {

	private String perfilDeRedeWiMax;
	private String servicio;
	private String numeroDeTelefono;
	private String comentarios;
	
	public WimaxResultDto() {
		super();
	}
	
	public WimaxResultDto(String perfilDeRedeWiMax, String servicio, String numeroDeTelefono, String comentarios) {
		super();
		this.perfilDeRedeWiMax = perfilDeRedeWiMax;
		this.servicio = servicio;
		this.numeroDeTelefono = numeroDeTelefono;
		this.comentarios = comentarios;
	}

	public String getPerfilDeRedeWiMax() {
		return perfilDeRedeWiMax;
	}
	
	public void setPerfilDeRedeWiMax(String perfilDeRedeWiMax) {
		this.perfilDeRedeWiMax = perfilDeRedeWiMax;
	}
	
	public String getServicio() {
		return servicio;
	}
	
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public String getNumeroDeTelefono() {
		return numeroDeTelefono;
	}
	public void setNumeroDeTelefono(String numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "WimaxResultDto [perfilDeRedeWiMax=" + perfilDeRedeWiMax + ", servicio=" + servicio
				+ ", numeroDeTelefono=" + numeroDeTelefono + ", comentarios=" + comentarios + "]";
	}
}
