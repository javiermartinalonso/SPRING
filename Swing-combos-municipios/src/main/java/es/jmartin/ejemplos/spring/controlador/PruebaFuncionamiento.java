package es.jmartin.ejemplos.spring.controlador;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.jmartin.ejemplos.spring.model.dto.Pais;
import es.jmartin.ejemplos.spring.model.repository.MunicipioRepository;
import es.jmartin.ejemplos.spring.model.repository.PaisRepository;
import es.jmartin.ejemplos.spring.model.repository.ProvinciaRepository;

public class PruebaFuncionamiento
{
	public static void main(String[] args)
	{
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/spring/app-context.xml");
		
		PaisRepository paisRepository = (PaisRepository) contexto.getBean("paisRepository");
		
		ProvinciaRepository provinciaRepository = (ProvinciaRepository) contexto.getBean("provinciaRepository");
		
		MunicipioRepository municipioRepository = (MunicipioRepository) contexto.getBean("municipioRepository");
		
		Pais pais = new Pais(1, "paisano", null);
		
		paisRepository.save(pais);
		
		Pais paisBBDD = paisRepository.findOne(pais.getIdpais());
				
		System.out.println(paisBBDD.toString());
		
		
		System.out.println(paisRepository.findAll().toString());
		System.out.println(provinciaRepository.findAll().toString());
		System.out.println(municipioRepository.findAll().toString());
	}
}