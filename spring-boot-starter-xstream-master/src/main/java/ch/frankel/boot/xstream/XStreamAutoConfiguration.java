package ch.frankel.boot.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

@Configuration
public class XStreamAutoConfiguration {
	
	
	//Sobrescribo el bean del driver para que se autoinjecte con el tipo JSON en vez de XML
	//si la propiedad del properties es true
    @Bean
    @ConditionalOnProperty(value = "ch.frankel.boot.xstream.json", havingValue = "true")
    public HierarchicalStreamDriver driver() {
        return new JsonHierarchicalStreamDriver();
    }
    
	//Creo una instancia de XStream e el caso de que no
	// exista ya en el contexto de spring
    @Bean
    @ConditionalOnMissingBean(XStream.class)
    public XStream xStream(Optional<HierarchicalStreamDriver> driver) {
        System.out.println("bean xstream in XStreamAutoConfiguration.class has called");
        return new XStream(driver.orElse(new XppDriver()));
    }

    
    @Configuration
    public static class XStreamConverterAutoConfiguration {

        @Autowired
        private XStream xstream;

        @Autowired
        private Collection<Converter> converters;

        @PostConstruct
        public void registerConverters() {
            converters.forEach(xstream::registerConverter);
        }
    }
}
