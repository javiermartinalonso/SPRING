package es.jmartin.springbootwsclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.jmartin.springbootwsclient.client.QuoteClient;
import es.jmartin.springbootwsclient.domain.wsdl.GetQuoteResponse;

@SpringBootApplication
public class SpringBootWsClientApplication {

	
	/**
	 * El método main() se remite a la clase auxiliar SpringApplication, 
	 * proporcionando QuoteConfiguration.class como argumento su método run().
	 * Al arrancar buscará todas las clases anotadas con @Configuration
	 * Esto le dice a Spring que lea los metadatos de la anotación QuoteConfiguration 
	 * y que los administre como un componente en el contexto de la aplicación Spring.
	 * 
	 * Esta aplicación está codificada para buscar el código postal 94304, Palo Alto, CA. 
	 * Hacia el final de esta guía, verá cómo conectar un código postal diferente sin editar el código.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWsClientApplication.class, args);
	}
	
	@Bean
	CommandLineRunner lookup(QuoteClient quoteClient) {
		return args -> {
			String ticker = "MSFT";

			if (args.length > 0) {
				ticker = args[0];
			}
			GetQuoteResponse response = quoteClient.getQuote(ticker);
			System.err.println(response.getGetQuoteResult());
		};
	}	
}
