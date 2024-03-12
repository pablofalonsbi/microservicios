package com.pablo.carroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarroserviceApplication.class, args);
	}

}
