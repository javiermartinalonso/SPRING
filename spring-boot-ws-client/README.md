[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.png?v=103)](https://github.com/ellerbrock/open-source-badge/)

# Ejemplo sencillo de spring boot web-client con SOAP #


Aplicación ***Spring-boot+>spring-boot-starter-web-services***, generada con el wizard ***Spring Started Project*** del [**IDE Spring Tool Suite (STS)**](https://spring.io/tools "IDE Spring Tool Suite")

Esta guía lo guiará por el proceso de consumir un servicio web basado en SOAP con Spring.

Partimos de la guia [https://spring.io/guides/gs/consuming-web-service/](https://spring.io/guides/gs/consuming-web-service/ "https://spring.io/guides/gs/consuming-web-service/").

## Lo que vas a construir ##

Construirá un cliente que obtenga datos meteorológicos de un servicio web remoto basado en WSDL utilizando SOAP . Puede obtener más información sobre el servicio de presupuesto en http://www.webservicex.com/stockquote.asmx?op=GetQuote .

El servicio ofrece cotizaciones bursátiles. Podrás usar tu propio símbolo de cotización.

## Página de explicación de su construcción ##

[https://spring.io/guides/gs/consuming-web-service/](https://spring.io/guides/gs/consuming-web-service/ "https://spring.io/guides/gs/consuming-web-service/").

## Requisitos ##

- **Alrededor de 15 minutos**
- **Un editor de texto favorito o IDE**
- **JDK 1.8 o posterior**
- **Gradle 2.3+ o Maven 3.0+**

## Compilar y pasar los test ##

Con maven desde la carpeta raiz que contiene el .pom del módulo, ejecutamos:

    mvn clean install



## Pasos en la creación del proyecto ##

- Crear esqueleto con el wizard, seleccionando como jar. 

- Generar objetos de dominio basados ​​en WSDL

	La interfaz a un servicio web SOAP se captura en un WSDL . JAXB proporciona un medio sencillo para generar clases de Java a partir de un WSDL (o más bien: el XSD contenido en la <Types/>sección del WSDL). El WSDL para el servicio de presupuesto se puede encontrar en http://www.webservicex.com/stockquote.asmx?WSDL .
	
	Para generar clases Java desde el WSDL en maven, necesita la siguiente configuración de complemento:
	
		<plugin>
		    <groupId>org.jvnet.jaxb2.maven2</groupId>
		    <artifactId>maven-jaxb2-plugin</artifactId>
		    <version>0.13.1</version>
		    <executions>
		        <execution>
		            <goals>
		                <goal>generate</goal>
		            </goals>
		        </execution>
		    </executions>
		    <configuration>
		        <schemaLanguage>WSDL</schemaLanguage>
		        <generatePackage>domain.wsdl</generatePackage>
		        <schemas>
		            <schema>
		                <url>http://www.webservicex.com/stockquote.asmx?WSDL</url>
		            </schema>
		        </schemas>
		    </configuration>
		</plugin>
	
	Esta configuración generará clases para el WSDL encontrado en la URL especificada, colocando esas clases en el paquete domain.wsdl.

	Yo lo que he hecho a continuación es copiarlos del paquete generado en target y traermelos a un paquete dentro del claspath en src/main/java para poder usar las clases como objetos de dominio para hacer marshal y unmarshal de los mensajes soap.

- Crear un cliente de servicio meteorológico

	Para crear un cliente de servicio web, simplemente tiene que extender la clase WebServiceGatewaySupport y codificar sus operaciones:
	
	`src/main/java/hello/QuoteClient.java`

		package es.jmartin.springbootwsclient;
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
		import org.springframework.ws.soap.client.core.SoapActionCallback;
		
		import es.jmartin.springbootwsclient.domain.wsdl.GetQuote;
		import es.jmartin.springbootwsclient.domain.wsdl.GetQuoteResponse;
		
		/**
		 * Para crear un cliente de servicio web, simplemente tiene que extender la clase WebServiceGatewaySupport y codificar sus operaciones
		 * 
		 * 
		 * @author javier.martin
		 *
		 */
		public class QuoteClient extends WebServiceGatewaySupport {
		
			private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);
		
			
			/**
			 * El cliente contiene un método: getQuote que realiza el intercambio real de SOAP.
			 * 
			 * En este método, tanto la clase GetQuote como las clases GetQuoteResponse se derivan del WSDL 
			 * y se generaron en el proceso de generación JAXB descrito en el paso anterior. 
			 * Crea el objeto GetQuote de solicitud y lo configura con el parámetro ticker. 
			 * Después de imprimir el código de cotización, usa WebServiceTemplate suministrado por la clase base WebServiceGatewaySupport de la que extendemos,
			 * para realizar el intercambio real del mensaje SOAP. Pasa el objeto request de tipo GetQuote, así como un objeto SoapActionCallback 
			 * para pasar un encabezado SOAPAction con la solicitud, ya que el WSDL describió que necesitaba este encabezado 
			 * en los elementos <soap:operation/>. Emite la respuesta en un objeto GetQuoteResponse, que luego se devuelve.
			 * @param ticker
			 * @return
			 */
			public GetQuoteResponse getQuote(String ticker) {
		
				GetQuote request = new GetQuote();
				request.setSymbol(ticker);
		
				log.info("Requesting quote for " + ticker);
		
				GetQuoteResponse response = (GetQuoteResponse) getWebServiceTemplate()
						.marshalSendAndReceive("http://www.webservicex.com/stockquote.asmx",
								request,
								new SoapActionCallback("http://www.webserviceX.NET/GetQuote"));
		
				return response;
			}
		
		}

- Configuración de componentes del servicio web

	Spring WS utiliza el módulo OXM de Spring Framework que tiene la función Jaxb2Marshallerde serializar y deserializar las solicitudes XML.
	
	`src/main/java/hello/QuoteConfiguration.java`


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



- Hacer la aplicación ejecutable

	Esta aplicación está empaquetada para ejecutarse desde la consola y recuperar un único pronóstico del tiempo para un código postal dado.
	
	`src/main/java/hello/SpringBootWsClientApplication.java`

- Construye un JAR ejecutable





### Ejecuntando la aplicación ###

En este punto nuestra aplicación debe funcionar. Puesto que hemos utilizado el POM de ***spring-boot-starter-parent***, tenemos un método run de ejecución que podemos usar para iniciar la aplicación. Escribe `mvn spring-boot:run` desde línea de comandos en el directorio raíz del proyecto para iniciar la aplicación:

O puedes construir el archivo JAR con `./mvnw clean package`. Entonces puedes ejecutar el archivo JAR:

`java -jar target/spring-boot-ws-client-0.0.1-SNAPSHOT.jar`


	C:\00_DESCARGAS\NAVEGADORES_WEB\gs-consuming-web-service-master\complete>java -jar target/gs-consuming-web-service-0.1.0.jar
	
	  .   ____  ___ _ _
	 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
	( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
	 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
	  '  |____| .__|_| |_|_| |_\__, | / / / /
	 =========|_|==============|___/=/_/_/_/
	 :: Spring Boot ::(v1.5.8.RELEASE)
	
	2017-10-31 13:08:31.894  INFO 8152 --- [   main] hello.Application: Starting Application v0.1.0 on PC-MP05RUV with PID 8152 (C:\
	00_DESCARGAS\NAVEGADORES_WEB\gs-consuming-web-service-master\complete\target\gs-consuming-web-service-0.1.0.jar started by javier.martin in C:\00_DESCARGAS\NAVE
	GADORES_WEB\gs-consuming-web-service-master\complete)
	2017-10-31 13:08:31.901  INFO 8152 --- [   main] hello.Application: No active profile set, falling back to default profiles: def
	ault
	2017-10-31 13:08:32.031  INFO 8152 --- [   main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.Annotation
	ConfigApplicationContext@5d6f64b1: startup date [Tue Oct 31 13:08:32 CET 2017]; root of context hierarchy
	2017-10-31 13:08:33.156  INFO 8152 --- [   main] o.s.oxm.jaxb.Jaxb2Marshaller : Creating JAXBContext with context path [hello.wsdl]
	2017-10-31 13:08:33.263  INFO 8152 --- [   main] o.s.ws.soap.saaj.SaajSoapMessageFactory  : Creating SAAJ 1.3 MessageFactory with SOAP 1.1 Protocol
	2017-10-31 13:08:33.273 DEBUG 8152 --- [   main] o.s.ws.soap.saaj.SaajSoapMessageFactory  : Using MessageFactory class [com.sun.xml.internal.messaging.s
	aaj.soap.ver1_1.SOAPMessageFactory1_1Impl]
	2017-10-31 13:08:33.638  INFO 8152 --- [   main] o.s.j.e.a.AnnotationMBeanExporter: Registering beans for JMX exposure on startup
	2017-10-31 13:08:33.662  INFO 8152 --- [   main] hello.QuoteClient: Requesting quote for MSFT
	2017-10-31 13:08:33.680 DEBUG 8152 --- [   main] o.s.ws.client.core.WebServiceTemplate: Opening [org.springframework.ws.transport.http.HttpUrlConnec
	tion@3e6fa38a] to [http://www.webservicex.com/stockquote.asmx]
	2017-10-31 13:08:33.748 DEBUG 8152 --- [   main] o.s.ws.client.MessageTracing.sent: Sent request [SaajSoapMessage {http://www.webserviceX.NET/}G
	etQuote]
	2017-10-31 13:08:34.507 DEBUG 8152 --- [   main] o.s.ws.client.MessageTracing.received: Received response [SaajSoapMessage {http://www.webserviceX.N
	ET/}GetQuoteResponse] for request [SaajSoapMessage {http://www.webserviceX.NET/}GetQuote]
	exception
	2017-10-31 13:08:34.520  INFO 8152 --- [   main] hello.Application: Started Application in 3.756 seconds (JVM running for 4.473)
	
	2017-10-31 13:08:34.522  INFO 8152 --- [   Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationCon
	figApplicationContext@5d6f64b1: startup date [Tue Oct 31 13:08:32 CET 2017]; root of context hierarchy
	2017-10-31 13:08:34.524  INFO 8152 --- [   Thread-2] o.s.j.e.a.AnnotationMBeanExporter: Unregistering JMX-exposed beans on shutdown

Se muestra la salida de registro. El servicio debería estar en funcionamiento dentro de unos segundos.

    Requesting quote for MSFT
    
    <StockQuotes><Stock><Symbol>MSFT</Symbol><Last>62.70</Last>...</StockQuotes>

Puede enchufar un teletipo diferente escribiendo java -jar target/gs-consuming-web-service-0.1.0.jar ORCL

    Requesting quote for ORCL
    
    <StockQuotes><Stock><Symbol>ORCL</Symbol><Last>39.26</Last>...</StockQuotes>
    Resumen

¡Felicitaciones! Acaba de desarrollar un cliente para consumir un servicio web basado en SOAP con Spring.