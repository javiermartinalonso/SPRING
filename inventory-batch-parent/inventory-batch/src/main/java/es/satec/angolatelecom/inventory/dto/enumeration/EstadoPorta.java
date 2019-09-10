package es.satec.angolatelecom.inventory.dto.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum EstadoPorta {
	BLOQUEADO("BLOQUEADO"),
	LIVRE("LIVRE"),
	RESERVADO("RESERVADO"),
	OCUPADO("OCUPADO");
	
	private static Map<String, EstadoPorta> m = new HashMap<>();
	
	static {
		for (EstadoPorta er : EstadoPorta.values()) {
			m.put(er.getValue(), er);
		}
	}
	
	private String sValue;
	
	EstadoPorta(String sValue) {
		this.sValue = sValue;
	}
	
	public String getValue() {
		return this.sValue;
	}
	
	public static EstadoPorta fromValue(String s) {
		return m.get(s);
	}

}
