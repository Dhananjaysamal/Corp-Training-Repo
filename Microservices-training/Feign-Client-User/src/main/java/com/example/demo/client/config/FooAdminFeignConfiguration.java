//package com.example.demo.client.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import feign.Logger;
//import feign.RequestInterceptor;
//import feign.auth.BasicAuthRequestInterceptor;
//import feign.slf4j.Slf4jLogger;
//
//@Configuration
//public class FooAdminFeignConfiguration {
//
//	 @Bean (name ="feign-admin-logger")
//	    public Logger feignLogger() {
//	        return new Slf4jLogger(FooAdminFeignConfiguration.class);
//	    }
//
//	    @Bean(name = "admin-request-interceptor")
//	    public RequestInterceptor basicAuthRequestInterceptor() {
//	        return new BasicAuthRequestInterceptor("admin", "admin");
//	    }
//}
