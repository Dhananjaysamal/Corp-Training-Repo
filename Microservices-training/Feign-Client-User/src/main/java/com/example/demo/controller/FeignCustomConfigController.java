//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.bean.User;
//import com.example.demo.client.FooUserClient;
//
//@RestController
//@RequestMapping("/FeignCustomConfig")
//public class FeignCustomConfigController {
//	
////	private FooAdminClient fooAdminClient;
//
//	private FooUserClient fooUserClient;
//
//    private Object special;
//
//    @Autowired(required = false)
//    @Qualifier("special")
//    public void setSpecial(Object special) {
//        this.special = special;
//    }
//    
//    @Autowired
//	public FeignCustomConfigController( FooUserClient fooUserClient) {
////		this.fooAdminClient = fooAdminClient;
//		this.fooUserClient = fooUserClient;
//    }
//
//	@RequestMapping("/user-order")
//    public ResponseEntity<List<User>> getUserFoos() {
//        List<User> foos = fooUserClient.getFoos();
//
//        return ResponseEntity.ok(foos); 
//    }
//
////	@RequestMapping("/admin-order")
////    public ResponseEntity<List<Order>> getAdminFoos() {
////        List<Order> foos = fooAdminClient.getFoos();
////
////        return ResponseEntity.ok(foos);
////    }
//
//}
