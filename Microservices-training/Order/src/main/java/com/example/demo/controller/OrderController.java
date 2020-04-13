package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Order;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	Environment environment;
	
	@GetMapping("/serviceInfo")
	public String getInfo() {
		String port = environment.getProperty("local.server.port");
		return "From activated service port is "+port;
	}

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

	@GetMapping("/{userId}")
	public List<Order> getAllById(@PathVariable String userId) {

		System.out.println("from order controller ===============>>" + userId);

		List<Order> orders = new ArrayList<>();

		Order order = new Order();
		order.setOrderId(103);
		order.setOrderDesc("desc3");
		orders.add(order);

		order = new Order();
		order.setOrderId(104);
		order.setOrderDesc("desc4");
		orders.add(order);

		return orders;
	}
	@PostMapping(value = "/getPathVariableFromPostBody")
	public Order	getPathVariableFromPostBody(@RequestBody Order order) {

		order.setOrderDesc(order.getOrderDesc() + "  order desc updated");
		return order;
	}

	@PostMapping("/getRequestParamFromPostBody")
	public Order getRequestParamFromPostBody(@RequestParam("orderId") int orderId) {

		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderDesc("abc");
		return order;

	}
	
//	@GetMapping("/foos")
//	public ResponseEntity<List<User>> getFoos(Principal currentUser) {
//		Authentication userAuthentication = (Authentication) currentUser;
//
//
//		if (userAuthentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
//			return ResponseEntity.ok(Arrays.asList(new User(1, "admin1"), new User(2, "admin2")));
//		} else {
//			return ResponseEntity.ok(Arrays.asList(new User(3, "user1"), new User(4, "user2")));
//		}
//	}

}
