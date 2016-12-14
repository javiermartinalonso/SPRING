package es.jmartin.ejemplos.spring.controlador;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Inicio {

//	public static ApplicationContext contexto = new ClassPathXmlApplicationContext("/spring/app-context.xml");
	
	ApplicationContext contexto = new ClassPathXmlApplicationContext("/spring/app-context.xml");
	
	public static void main(String[] args) {
				
		Ventana ventana = new Ventana();
	}
}
