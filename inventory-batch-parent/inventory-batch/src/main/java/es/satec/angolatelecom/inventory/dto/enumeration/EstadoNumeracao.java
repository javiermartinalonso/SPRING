package es.satec.angolatelecom.inventory.dto.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum EstadoNumeracao {
	ATRIBUIDO("ATRIBUIDO"),
	LIVRE("LIVRE"),
	RESERVADO("RESERVADO"),
	OUTLINE("OUTLINE"),
	AGGING("AGGING");
	
	private static Map<String, EstadoNumeracao> m = new HashMap<>();
	
	static {
		for (EstadoNumeracao er : EstadoNumeracao.values()) {
			m.put(er.getValue(), er);
		}
	}
	
	private String sValue;
	
	EstadoNumeracao(String sValue) {
		this.sValue = sValue;
	}
	
	public String getValue() {
		return this.sValue;
	}
	
	public static EstadoNumeracao fromValue(String s) {
		return m.get(s);
	}

}
