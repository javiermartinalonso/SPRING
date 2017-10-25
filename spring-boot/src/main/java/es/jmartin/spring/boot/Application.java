package es.jmartin.spring.boot;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application 
{
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) 
	{
		log.info("################ INICIO MAIN #####################");
		SpringApplication.run(Application.class, args);
		log.info("################# TEST LOGS ######################");
		testLogs();
		log.info("################# FIN MAIN #######################");
	}

	private static void testLogs() 
	{
		log.info("------- STARTED 'provision-api' -------------");
		log.info("URL 'Swagger': http://localhost:8080/swagger-ui.html");
		log.info("----------------------------------------------------");

		log.trace("LogTest - TRACE");
		log.debug("LogTest - DEBUG");
		log.info("LogTest - INFO");
		log.warn("LogTest - WARN");
		log.error("LogTest - ERROR");
	}
}