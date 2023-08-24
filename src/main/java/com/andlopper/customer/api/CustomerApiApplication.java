package com.andlopper.customer.api;

import com.andlopper.customer.api.service.CustomerService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApiApplication {
	private static final Logger log = LoggerFactory.getLogger(CustomerApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);

		log.info("Programa iniciado");
	}
}
