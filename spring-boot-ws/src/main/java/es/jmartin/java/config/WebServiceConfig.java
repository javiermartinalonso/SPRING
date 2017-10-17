package es.jmartin.java.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Primavera WS utiliza un tipo diferente de servlets para el manejo de mensajes SOAP: MessageDispatcherServlet. 
 * Es importante inyectar y establecer ApplicationContexta MessageDispatcherServlet. 
 * Sin eso, Spring WS no detectará Spring beans automáticamente.
 * Al nombrar este bean messageDispatcherServlet, no reemplaza el DispatcherServletbean predeterminado
 * de Spring Boot .
 * DefaultMethodEndpointAdapterconfigura el modelo de programación Spring WS impulsado por la anotación.
 * Esto hace posible usar las diferentes anotaciones como se @Endpointmencionó anteriormente.
 * DefaultWsdl11Definition expone un WSDL 1.1 estándar usando XsdSchema
 * Es importante notar que necesita especificar nombres de bean
 * para MessageDispatcherServlety DefaultWsdl11Definition. Los nombres de los bean determinan
 * la URL bajo la cual el servicio web y el archivo WSDL generado están disponibles. En este caso,
 * el WSDL estará disponible en http://<host>:<port>/ws/countries.wsdl.
 * 
 * Esta configuración también utiliza la transformación de servlet de ubicación
 * WSDL servlet.setTransformWsdlLocations(true).
 * Si visita http: // localhost: 8080 / ws / countries.wsdl ,
 * soap:addresstendrá la dirección correcta. Si, por el contrario,
 * visita el WSDL desde la dirección IP pública que tiene asignada a su máquina,
 * verá esa dirección en su lugar.
 */


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CountriesPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(countriesSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
	}
}