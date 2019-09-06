package es.satec.angolatelecom.sigo.sim.stock.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("es.satec.angolatelecom.sim.provision.service")
@EnableAutoConfiguration
@EntityScan(basePackages = {"es.satec.angolatelecom.sigo.sim.stock.service.dto"})
@EnableJpaRepositories(basePackages = {"es.satec.angolatelecom.sigo.sim.stock.service.repository"})
@EnableTransactionManagement
public class DbConfiguration {
/**
    @Bean
    public DataSource dataSource(){
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
       dataSource.setUrl("jdbc:oracle:thin:@213.134.50.3:1521/ATDEVMAD");
       dataSource.setUsername( "SIGOINT" );
       dataSource.setPassword( "SATEC2016" );
       return dataSource;
    }
    */
}
