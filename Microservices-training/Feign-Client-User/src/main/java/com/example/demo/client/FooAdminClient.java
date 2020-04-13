//package com.example.demo.client;
//
//import java.util.List;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.example.demo.bean.Order;
//import com.example.demo.client.config.FooAdminFeignConfiguration;
//
////@FeignClient(value = "ORDER-SERVICE", configuration = FooAdminFeignConfiguration.class)
//public interface FooAdminClient {
//	
//	@GetMapping("/foos")
//	    List<Order> getFoos();
//}
