package com.product.commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcommerceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductApplication.class, args);
	}

}
