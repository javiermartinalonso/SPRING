package es.satec.angolatelecom.inventory.dto.wimax;

public class WimaxSheetDto {

	private String mainkey;	
	private String eqNbrSerie;
	private String mac;
	private String passwordDados;
	private String passwordVoz;
	private String modeloTelefone;
	private String estado;
	
	public WimaxSheetDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WimaxSheetDto(String mainkey, String eqNbrSerie, String mac, String passwordDados, String passwordVoz,
			String modeloTelefone, String estado) {
		super();
		this.mainkey = mainkey;
		this.eqNbrSerie = eqNbrSerie;
		this.mac = mac;
		this.passwordDados = passwordDados;
		this.passwordVoz = passwordVoz;
		this.modeloTelefone = modeloTelefone;
		this.estado = estado;
	}

	public String getMainkey() {
		return mainkey;
	}

	public void setMainkey(String mainkey) {
		this.mainkey = mainkey;
	}

	public String getEqNbrSerie() {
		return eqNbrSerie;
	}

	public void setEqNbrSerie(String eqNbrSerie) {
		this.eqNbrSerie = eqNbrSerie;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPasswordDados() {
		return passwordDados;
	}

	public void setPasswordDados(String passwordDados) {
		this.passwordDados = passwordDados;
	}

	public String getPasswordVoz() {
		return passwordVoz;
	}

	public void setPasswordVoz(String passwordVoz) {
		this.passwordVoz = passwordVoz;
	}

	public String getModeloTelefone() {
		return modeloTelefone;
	}

	public void setModeloTelefone(String modeloTelefone) {
		this.modeloTelefone = modeloTelefone;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "WimaxSheetDto [mainkey=" + mainkey + ", eqNbrSerie=" + eqNbrSerie + ", mac=" + mac + ", passwordDados="
				+ passwordDados + ", passwordVoz=" + passwordVoz + ", modeloTelefone=" + modeloTelefone + ", estado="
				+ estado + "]";
	}
}
