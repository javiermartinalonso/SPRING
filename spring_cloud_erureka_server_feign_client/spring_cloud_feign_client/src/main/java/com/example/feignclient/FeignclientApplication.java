package com.example.feignclient;

import com.example.feignclient.accessor.RestAPIAccessor;
import com.example.feignclient.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignclientApplication implements CommandLineRunner{

	private Logger LOG = LoggerFactory.getLogger(FeignclientApplication.class);

	@Autowired
	private RestAPIAccessor restAPIAccessor;

	public static void main(String[] args) {
		SpringApplication.run(FeignclientApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		/*EL ID DEBE SER VALIDO EN LA BBDD H2 O ESTA APLICACION SE CAERA AL ARRANCARSE*/
		Product productFromFeignClient = restAPIAccessor.getProductFromRestAPI("2c9380106e1c1625016e1c1632490000");
		LOG.info("Product from RESTAPIAPPLICATION:" + productFromFeignClient.toString());
	
	}
}
