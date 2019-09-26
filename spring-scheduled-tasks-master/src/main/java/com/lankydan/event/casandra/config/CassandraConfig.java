package com.lankydan.event.casandra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {
 
    @Override
    protected String getKeyspaceName() {
        return "testKeySpace";
    }
 
    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = 
          new CassandraClusterFactoryBean();
        cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9142);
        return cluster;
    }
 
    @Bean
    public CassandraMappingContext cassandraMapping() 
      throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}