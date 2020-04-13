package com.example.demo.client.config;

import org.springframework.context.annotation.Bean;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;

public class CustomFeignConfiguration {

	    @Bean
	    public Contract feignContract() {
	        return new feign.Contract.Default();
	    }

	    @Bean
	    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	        return new BasicAuthRequestInterceptor("user", "password");
	    }

//	    @Bean
//	    public OkHttpClient client() {
//	        return new OkHttpClient();
//	    }

	}
