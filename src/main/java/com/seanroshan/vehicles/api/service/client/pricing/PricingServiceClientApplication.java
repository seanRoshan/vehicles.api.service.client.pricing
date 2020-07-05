package com.seanroshan.vehicles.api.service.client.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PricingServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingServiceClientApplication.class, args);
	}

}
