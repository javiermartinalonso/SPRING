package es.satec.angolatelecom.inventory.dto.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum EstadoRecurso {
	PLANEAMENTO("PLANEAMENTO"),
	INSTALACAO("INSTALACAO"),
	PRODUCAO("PRODUCAO"),
	FORAPRODUCAO("FORA-PRODUCAO"),
	RETIRADO("RETIRADO"),
	MANUTENCAO("MANUTENCAO");
	
	private static Map<String, EstadoRecurso> m = new HashMap<>();
	
	static {
		for (EstadoRecurso er : EstadoRecurso.values()) {
			m.put(er.getValue(), er);
		}
	}
	
	private String sValue;
	
	EstadoRecurso(String sValue) {
		this.sValue = sValue;
	}
	
	public String getValue() {
		return this.sValue;
	}
	
	public static EstadoRecurso fromValue(String s) {
		return m.get(s);
	}

}
