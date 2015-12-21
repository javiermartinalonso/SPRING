package es.jmartin.ejemplos.spring.data.controlador;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.jmartin.ejemplos.spring.data.model.domainobject.Pais;
import es.jmartin.ejemplos.spring.data.model.repository.PaisRepository;

public class PruebaFuncionamiento
{
	public static void main(String[] args)
	{
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/spring/app-context.xml");
		
		PaisRepository paisRepository = (PaisRepository) contexto.getBean("paisRepository");
		
		Pais pais = new Pais("Mi pais", "paisano", "midioma");
		
		paisRepository.save(pais);
				
		Pais paisBBDD = paisRepository.findOne((long) pais.getId());

		System.out.println(paisBBDD.toString());
	}
}