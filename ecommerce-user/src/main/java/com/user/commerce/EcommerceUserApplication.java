package com.user.commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class EcommerceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceUserApplication.class, args);
	}

}
