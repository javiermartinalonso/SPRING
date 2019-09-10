package es.satec.angolatelecom.inventory.dto.entities.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.Autogerados;
import es.satec.angolatelecom.inventory.dto.entities.annotations.ResourceType;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoNumeracao;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@ResourceType(inventoryClass = "NumeroDeTelefone", name = "phoneNumber")
public class NumeroDeTelefone extends Autogerados {

	private static final long serialVersionUID = -5905572173880289691L;
	private static final String CLASSNAME = "NumeroDeTelefone";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public NumeroDeTelefone() {
		super();
	}
	
	public NumeroDeTelefone(String name, EstadoNumeracao estado, Date dataAlteracaoEstado) {
		super(CLASSNAME, name);
		super.addProperty("estado", estado.getValue());
		super.addProperty("dataAlteracaoEstado", sdf.format(dataAlteracaoEstado));
	}

	/**
	 * @return the estado
	 */
	@JsonIgnore
	public String getEstado() {
		return (String) super.getProperty("estado");
	}
	
	@JsonIgnore
	public EstadoNumeracao getEstadoEnumerado() {
		return EstadoNumeracao.fromValue((String) super.getProperty("estado"));
	}

	/**
	 * @param estado the estado to set
	 */
	@JsonIgnore
	public void setEstado(EstadoNumeracao estado) {
		super.addProperty("estado", estado.getValue());
	}
	
	@JsonIgnore
	public void setEstado(String estado) {
		super.addProperty("estado", EstadoNumeracao.fromValue(estado).getValue());
	}

	/**
	 * @return the dataAlteracaoEstado
	 */
	@JsonIgnore
	public Date getDataAlteracaoEstado() {
		return (Date) super.getProperty("dataAlteracaoEstado");
	}

	/**
	 * @param dataAlteracaoEstado the dataAlteracaoEstado to set
	 */
	@JsonIgnore
	public void setDataAlteracaoEstado(Date dataAlteracaoEstado) {
		super.addProperty("dataAlteracaoEstado", sdf.format(dataAlteracaoEstado));
	}

}
