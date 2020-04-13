package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.client.config.RibbonConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "orderclient" ,configuration = RibbonConfiguration.class)
public class FeignClientUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientUserApplication.class, args);
	}

}
