package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Order;
import com.example.demo.client.UserFeignClient;

@RestController
@RequestMapping("/user")
public class FeignUserController {
	
	@Autowired
	UserFeignClient userFeignClient;
	
	@GetMapping("/serviceInfo")
	public String getInfo() {
		return userFeignClient.getInfo();
	}
	
	@GetMapping("/")
	public List<Order> getAllUserOrders(){
		return userFeignClient.getAllOrders();
	}
	
	@GetMapping("/{userId}")
	public List<Order> getAllById(@PathVariable("userId") String userId){
		return userFeignClient.getAllById(userId);
	}
	
	@PostMapping("/getPathVariableFromPostBody")
	public Order getPathVariableFromPostBody(@RequestBody Order order){
		return userFeignClient.getPathVariableFromPostBody(order);
	}
	
	@GetMapping("/getRequestParamFromPostBody")
	public Order getRequestParamFromPostBody(@RequestParam("orderId") int orderId) {
		return userFeignClient.getRequestParamPostBody(orderId);
	}

}
