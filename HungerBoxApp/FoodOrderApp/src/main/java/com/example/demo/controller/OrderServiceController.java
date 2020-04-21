package com.example.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.OrderResponse;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("")
public class OrderServiceController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/order/{userId}/{vendor}/{cardNumber}/{cvv}/{month}/{year}/{amount}/{toAccount}")
	public OrderResponse createOrder(@RequestBody OrderRequestDto orderRequestDto,
			@PathVariable int userId,
			@PathVariable String vendor,
			RedirectAttributes  redirectAttributes,@PathVariable("cardNumber") String cardNumber,@PathVariable("cvv") int cvv,@PathVariable("month") int month,@PathVariable("year")int year,@PathVariable("amount")double amount,@PathVariable("toAccount") long toAccount ) throws ParseException {

		
		redirectAttributes.addAttribute("toAccount", toAccount);
		redirectAttributes.addAttribute("amount", amount);
		
		return orderService.createOrder(orderRequestDto, userId, vendor,  cardNumber, cvv,month,year,amount,toAccount);
	}
	
	@GetMapping("/orderHistory/{userId}")
	public OrderResponse getOrderHistory(@PathVariable int userId) {
		
		return orderService.getOrderHistory(userId);
	}
}
