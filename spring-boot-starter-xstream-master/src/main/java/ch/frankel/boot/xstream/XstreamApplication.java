package ch.frankel.boot.xstream;

import static java.lang.System.err;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@SpringBootApplication
//@EnableJpaRepositories
public class XstreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(XstreamApplication.class, args);
	}
	
	@Bean
	@ConditionalOnClass(name="java.lang.String")
	public String classString() {
		String method = "classString()";
		err.println("Method " + method + " has been called");
		return method;
	}
	
	
	@Bean
	@ConditionalOnMissingClass("java.lang.String")
	public String missingClassString() {
		String method = "missingclassString()";
		err.println("Method " + method + " has been called");
		return method;
	}	
	
//	@Bean
//	public CommandLineRunner runner() {
//		return args -> {
//			err.println(new XStream().toXML(new Person("John", "Doe")));
//		};
//	}
	
	
	//Por medio del fichero de autoconfiguracion le inyecto un bean de Xstream
	@Bean
	public CommandLineRunner runner(XStream xstream) {
		return args -> {
			err.println(xstream.toXML(new Person("John", "Doe")));
		};
	}
	


/*	
	//Injecto y sobrescribo el bean de la configuracion por este, de modo que me genere un JSON
	@Bean
	public XStream xstream() {
		err.println("bean xstream in application.class has called");
			
		return new XStream(new JsonHierarchicalStreamDriver());
	}
	
	//Sobrescribo el bean del driver para que se autoinjecte con el tipo JSON en vez de XML
    @Bean
    @ConditionalOnProperty(value = "ch.frankel.boot.xstream.json", havingValue = "true")
    public HierarchicalStreamDriver driver() {
        return new JsonHierarchicalStreamDriver();
    }	
	
	//Injecto y sobrescribo el bean de la configuracion por este, de modo que me genere un JSON, por el tipo de driver con lo que lo creo
	@Bean
	public XStream xstream() {
		err.println("bean xstream in application.class has called");
			
		return new XStream(driver);
	}	
*/	
	
	//Creo un bean conversor personalizado de XStream
	@Bean 
	public Converter converter() {
	
		return new Converter() {
			
			@Override
			public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
				Person person = (Person) source;
//				writer.startNode(name:"name");
				writer.startNode("name");
				writer.setValue(person.firstName + " " + person.lastName);
				writer.endNode();
			}
			
			@Override
			public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext unmarshallingContext) {
				return null;
			}
			
			@Override
			public boolean canConvert(Class type) {
				return type == Person.class;
			}
		};
	}
	
	/*
	//lo muevo a mi fichero de configuracion para que cuando arranque el contexto de spring me haga esto mediante la autoconfiguracion
	//Registro todos los beans de tipo Converter existentes en el contexto de spring y los asocio a XStream
    @Bean
    public Collection<Converter> converters(XStream xstream, Collection<Converter> converters) {
        converters.forEach(xstream::registerConverter);
        return converters;
    }
    */
	
	
	
	public static class Person{
		public final String firstName;
		public final String lastName;
		
		public Person(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}
}
