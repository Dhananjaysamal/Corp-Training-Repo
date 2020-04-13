//package com.example.demo.client.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import feign.Logger;
//import feign.RequestInterceptor;
//import feign.auth.BasicAuthRequestInterceptor;
//import feign.slf4j.Slf4jLogger;
//
//@Configuration
//public class FooUserFeignConfiguration {
//	
//	@Bean(name = "feign-user-logger")
//	public Logger feignLogger() {
//		return new Slf4jLogger(FooUserFeignConfiguration.class);
//	}
//
//
//	@Bean(name = "user-request-interceptor")
//	public RequestInterceptor basicAuthRequestInterceptor() {
//		return new BasicAuthRequestInterceptor("user", "user");
//	}
//}
