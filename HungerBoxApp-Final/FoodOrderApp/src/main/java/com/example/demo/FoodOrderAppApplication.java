package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.config.LocalRibbonClientConfiguration;


@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "bank-service" , configuration = LocalRibbonClientConfiguration.class)
@EnableFeignClients
public class FoodOrderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderAppApplication.class, args);
	}

}
