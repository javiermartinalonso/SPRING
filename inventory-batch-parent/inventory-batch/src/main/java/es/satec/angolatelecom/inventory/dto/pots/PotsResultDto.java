package es.satec.angolatelecom.inventory.dto.pots;

public class PotsResultDto {

	private String central;
	private String mdf;
	private String parPrimarioCabo;
	private String parPrimarioArmario;
	private String armario;
	private String parSecundarioArmario;
	private String parCaixa;
	private String caixa;
	private String parCliente;
	private String direccionCliente;
	private String telefono;
	private String comentarios;
	
	public PotsResultDto()
	{
		super();
	}

	public String getCentral() {
		return central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public String getMdf() {
		return mdf;
	}

	public void setMdf(String mdf) {
		this.mdf = mdf;
	}

	public String getParPrimarioCabo() {
		return parPrimarioCabo;
	}

	public void setParPrimarioCabo(String parPrimarioCabo) {
		this.parPrimarioCabo = parPrimarioCabo;
	}

	public String getParPrimarioArmario() {
		return parPrimarioArmario;
	}

	public void setParPrimarioArmario(String parPrimarioArmario) {
		this.parPrimarioArmario = parPrimarioArmario;
	}

	public String getArmario() {
		return armario;
	}

	public void setArmario(String armario) {
		this.armario = armario;
	}

	public String getParSecundarioArmario() {
		return parSecundarioArmario;
	}

	public void setParSecundarioArmario(String parSecundarioArmario) {
		this.parSecundarioArmario = parSecundarioArmario;
	}

	public String getParCaixa() {
		return parCaixa;
	}

	public void setParCaixa(String parCaixa) {
		this.parCaixa = parCaixa;
	}

	public String getCaixa() {
		return caixa;
	}

	public void setCaixa(String caixa) {
		this.caixa = caixa;
	}

	public String getParCliente() {
		return parCliente;
	}

	public void setParCliente(String parCliente) {
		this.parCliente = parCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "PotsResult [central=" + central + ", mdf=" + mdf + ", parPrimarioCabo=" + parPrimarioCabo
				+ ", parPrimarioArmario=" + parPrimarioArmario + ", armario=" + armario + ", parSecundarioArmario="
				+ parSecundarioArmario + ", parCaixa=" + parCaixa + ", caixa=" + caixa + ", parCliente=" + parCliente
				+ ", direccionCliente=" + direccionCliente + ", telefono=" + telefono + ", comentarios=" + comentarios
				+ "]";
	}
}
