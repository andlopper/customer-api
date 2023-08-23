package com.andlopper.customer.api;

import com.andlopper.customer.api.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApiApplication {
	private static final Logger log = LoggerFactory.getLogger(CustomerApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);

		log.info("Programa iniciado");
	}

}
