package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MenuItemResponseDto;
import com.example.demo.entity.MenuItemEntity;
import com.example.demo.service.UserRegService;

@RestController
@RequestMapping("")
public class UserServiceController {

	@Autowired
	private UserRegService userRegService;

	@GetMapping("/searchMenu/{menuItem}")
	private List<MenuItemResponseDto> searchMenu(@PathVariable("")String menuItem) {
		List<MenuItemEntity> menuItemEntities = userRegService.findMenuItems(menuItem);
		
		return userRegService.convertToResponseDTO(menuItemEntities);
	}
	
	@GetMapping("/searchVendor/{vendorName}")
	private List<MenuItemResponseDto> searchVendor(
			@PathVariable("vendorName") String vendorName) {
		
		return userRegService.findMenuItemsForVendor(vendorName);
	}
}
