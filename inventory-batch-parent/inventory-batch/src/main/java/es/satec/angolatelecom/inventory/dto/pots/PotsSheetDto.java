package es.satec.angolatelecom.inventory.dto.pots;

public class PotsSheetDto {

	private String central;
	private String nomeCentral;
	private String nomeCabo;
	private String parCabo;
	private String nomeArmario;
	private String parPrimArm;
	private String assinante;
	private String parSecArm;
	private String nomeCaixa;
	private String parCaixa;
	private String estadoAssinante;
	private String app;
	
	public PotsSheetDto()
	{
		super();
	}
	
	public PotsSheetDto(String central, String nomeCentral, String nomeCabo, String parCabo, String nomeArmario,
			String parPrimArm, String assinante, String parSecArm, String nomeCaixa, String parCaixa,
			String estadoAssinante, String app) {
		super();
		this.central = central;
		this.nomeCentral = nomeCentral;
		this.nomeCabo = nomeCabo;
		this.parCabo = parCabo;
		this.nomeArmario = nomeArmario;
		this.parPrimArm = parPrimArm;
		this.assinante = assinante;
		this.parSecArm = parSecArm;
		this.nomeCaixa = nomeCaixa;
		this.parCaixa = parCaixa;
		this.estadoAssinante = estadoAssinante;
		this.app = app;
	}
	
	public String getCentral() {
		return central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public String getNomeCentral() {
		return nomeCentral;
	}

	public void setNomeCentral(String nomeCentral) {
		this.nomeCentral = nomeCentral;
	}

	public String getNomeCabo() {
		return nomeCabo;
	}

	public void setNomeCabo(String nomeCabo) {
		this.nomeCabo = nomeCabo;
	}

	public String getParCabo() {
		return parCabo;
	}

	public void setParCabo(String parCabo) {
		this.parCabo = parCabo;
	}

	public String getNomeArmario() {
		return nomeArmario;
	}

	public void setNomeArmario(String nomeArmario) {
		this.nomeArmario = nomeArmario;
	}

	public String getParPrimArm() {
		return parPrimArm;
	}

	public void setParPrimArm(String parPrimArm) {
		this.parPrimArm = parPrimArm;
	}

	public String getAssinante() {
		return assinante;
	}

	public void setAssinante(String assinante) {
		this.assinante = assinante;
	}

	public String getParSecArm() {
		return parSecArm;
	}

	public void setParSecArm(String parSecArm) {
		this.parSecArm = parSecArm;
	}

	public String getNomeCaixa() {
		return nomeCaixa;
	}

	public void setNomeCaixa(String nomeCaixa) {
		this.nomeCaixa = nomeCaixa;
	}

	public String getParCaixa() {
		return parCaixa;
	}

	public void setParCaixa(String parCaixa) {
		this.parCaixa = parCaixa;
	}

	public String getEstadoAssinante() {
		return estadoAssinante;
	}

	public void setEstadoAssinante(String estadoAssinante) {
		this.estadoAssinante = estadoAssinante;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "PotsSheet [central=" + central + ", NomeCentral=" + nomeCentral + ", NomeCabo=" + nomeCabo
				+ ", ParCabo=" + parCabo + ", NomeArmario=" + nomeArmario + ", ParPrimArm=" + parPrimArm
				+ ", Assinante=" + assinante + ", ParSecArm=" + parSecArm + ", NomeCaixa=" + nomeCaixa + ", ParCaixa="
				+ parCaixa + ", EstadoAssinante=" + estadoAssinante + ", App=" + app + "]";
	}	
}
