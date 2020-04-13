//package com.example.demo.client;
//
//import java.util.List;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.example.demo.bean.User;
//import com.example.demo.client.config.FooUserFeignConfiguration;
//
//@FeignClient(name = "http://ORDER-SERVICE/orderApp/order/" , configuration = FooUserFeignConfiguration.class)
//public interface FooUserClient {
//
//	
//	@GetMapping("/foos")
//    List<User> getFoos();
//	
//}
