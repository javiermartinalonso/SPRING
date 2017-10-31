package es.jmartin.springbootwsclient.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class QuoteConfiguration {

	/**
	 * El marshallerapunta a la colección de objetos de dominio generados 
	 * y los usará para serializar y deserializar entre XML y POJO 
	 * @return
	 */
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("es.jmartin.springbootwsclient.domain.wsdl");
		return marshaller;
	}

	
	/**
	 * crea y configura con el URI del servicio meteorológico que se muestra arriba. 
	 * También está configurado para usar el marcador de referencias JAXB.
	 * @param marshaller
	 * @return
	 */
	@Bean
	public QuoteClient quoteClient(Jaxb2Marshaller marshaller) {
		QuoteClient client = new QuoteClient();
		client.setDefaultUri("http://www.webservicex.com/stockquote.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}