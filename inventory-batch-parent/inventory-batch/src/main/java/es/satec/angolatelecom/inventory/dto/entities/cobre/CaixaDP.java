package es.satec.angolatelecom.inventory.dto.entities.cobre;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.satec.angolatelecom.inventory.dto.entities.NetworkElementCobertura;
import es.satec.angolatelecom.inventory.dto.enumeration.EstadoRecurso;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaixaDP extends NetworkElementCobertura {

	private static final long serialVersionUID = -7836581504189353479L;
	private static final String CLASSNAME = "CaixaDP";
	
	public CaixaDP() {
		super();
	}
	
	public CaixaDP(String name, EstadoRecurso estado, Double latitude, Double longitude, Map<String, Float> cobertura) {
		super(CLASSNAME, name, estado, latitude, longitude, cobertura);
	}

	/**
	 * @return the posicao
	 */
	@JsonIgnore
	public String getPosicao() {
		return (String) super.getProperty("posicao");
	}
	
	/**
	 * @param posicao the posicao to set
	 */
	@JsonIgnore
	public void setPosicion(String posicao) {
		super.addProperty("posicao", posicao);
	}
	
}
