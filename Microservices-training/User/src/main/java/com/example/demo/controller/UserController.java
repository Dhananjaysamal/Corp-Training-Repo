package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.bean.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public ResponseEntity<String> getAllOrders(){

		String uri ="http://ORDER-SERVICE/orderApp/order/";

		ResponseEntity<String> result =	restTemplate.getForEntity(uri, String.class);

		return result;
	}

	@GetMapping("/getForObject")
	public String getForObject(){

		String uri ="http://ORDER-SERVICE/orderApp/order/";

		String result =	restTemplate.getForObject(uri, String.class);

		return result;
	}

	@GetMapping("/postPathVariable/{orderId}/{orderDesc}")
	public  ResponseEntity<String> postPathVariable(@PathVariable("orderId") int orderId, @PathVariable("orderDesc") String orderDesc){

		String uri ="http://ORDER-SERVICE/orderApp/order/getPathVariableFromPostBody";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject request = new JSONObject();
		request.put("orderId", orderId);
		request.put("orderDesc", orderDesc);
		HttpEntity<?> httpEntity  = new HttpEntity<>( request.toString(),httpHeaders); 
		RestTemplate restTemplate  = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,String.class);

		return result;

	}

	@GetMapping("/postRequestParam")
	public ResponseEntity<String> postRequestParam(@RequestParam("orderId") int orderId){
		
		String uri ="http://ORDER-SERVICE/orderApp/order/getRequestParamFromPostBody";
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
				.queryParam("orderId", orderId);


		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(
				builder.toUriString(), 
				HttpMethod.POST, 
				entity, 
				String.class);

		return response;

	}


	@LoadBalanced
	@Bean
	public RestTemplate	getRestTemplate(){
		return new RestTemplate();
	}

}
