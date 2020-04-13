package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Order;

//@FeignClient(value = "orderservice", url = "${app.user.url}")
@FeignClient(name = "http://ORDER-SERVICE/orderApp/order/")
public interface UserFeignClient {
	
	@GetMapping("/")
	public List<Order> getAllOrders();
	
	@GetMapping("/{userId}")
	public List<Order> getAllById(@PathVariable("userId") String userId);
	
	
	@PostMapping("/getPathVariableFromPostBody")
	public Order getPathVariableFromPostBody(@RequestBody Order order);
	
	@PostMapping("/getRequestParamFromPostBody")
	public Order getRequestParamPostBody(@RequestParam("orderId") int orderId);
	
	@GetMapping("/serviceInfo")
	public String getInfo();

}
