package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.bean.Order;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@GetMapping("/")
	public List<Order>  getAllOrders(){
		
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setOrderId(10);
		order.setOrderDesc("shoes");
		
		Order	order1 = new Order();
		order1.setOrderDesc("lace");
		order1.setOrderId(11);
		
		orders.add(order);
		orders.add(order1);
		
		return orders;
	}
	@PostMapping(value = "/getPathVariableFromPostBody")
  public Order	getPathVariableFromPostBody(@RequestBody Order order) {
	  
		order.setOrderDesc(order.getOrderDesc() + "  order desc param send by user");
		return order;
  }
	
	@PostMapping("/getRequestParamFromPostBody")
	public Order getRequestParamFromPostBody(@RequestParam("orderId") int orderId) {
		
		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderDesc("abc");
		return order;
		
	}
	
}
