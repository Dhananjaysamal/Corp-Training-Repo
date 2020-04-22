package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MenuItemDto;
import com.example.demo.dto.MenuItemResponseDto;
import com.example.demo.entity.FoodVendor;
import com.example.demo.entity.MenuItemEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.VendorNotExistException;
import com.example.demo.repository.FoodVendorRepo;
import com.example.demo.repository.MenuItemRepo;
import com.example.demo.repository.UserRegRepo;

@Service
public class UserRegService {

	@Autowired
	UserRegRepo userRegRepo;
	
	@Autowired
	MenuItemRepo menuItemRepo;
	
	@Autowired
	FoodVendorRepo foodVendorRepo;
	
	public UserEntity saveRegisteredUserDetails(UserEntity userRegistrationEntity) {
		return userRegRepo.save(userRegistrationEntity);
	}
	
	public UserEntity findUserByMobileNumber(String mobileNumber) {
		return userRegRepo.findByMobileNumber(mobileNumber);
	}

	public List<MenuItemEntity> findMenuItems(String itemName) {

		List<MenuItemEntity> items = menuItemRepo.findByItemName(itemName);

		return items;
	}
	
	public List<MenuItemResponseDto>  convertToResponseDTO(List<MenuItemEntity> menuItemEntities){
		
		List<MenuItemResponseDto> menuItemResponseDtos = new ArrayList<MenuItemResponseDto>();
		
		menuItemEntities.stream().forEach(entity ->{
			MenuItemResponseDto menuItemResponseDto= new MenuItemResponseDto(); 
			menuItemResponseDto.setRestaurantLocation(entity.getFoodVendor().getLocation());
			menuItemResponseDto.setRestaurantName(entity.getFoodVendor().getName());
			
			MenuItemDto menuItemDto = new MenuItemDto();
			
			menuItemDto.setDesc(entity.getDesc());
			menuItemDto.setItemName(entity.getItemName());
			menuItemDto.setMenuType(entity.getMenuType());
			menuItemDto.setPrice(entity.getPrice());
			
			menuItemResponseDto.setItemDto(menuItemDto);
			
			menuItemResponseDtos.add(menuItemResponseDto);
		});
		return menuItemResponseDtos;
	}
	
	public List<MenuItemResponseDto> findMenuItemsForVendor(String name) {
		FoodVendor vendor = foodVendorRepo.findByName(name);
		List<MenuItemResponseDto> itemResponses=null;
		if(vendor!=null) {
			Set<MenuItemEntity> menuItems = vendor.getMenuItems();
			List<MenuItemEntity> menuItemEntities = menuItems.stream().collect(Collectors.toList());
			
			itemResponses=convertToResponseDTO(menuItemEntities);
		} else {
			throw new VendorNotExistException("Vendor not exist in system");
		}
		return itemResponses;
	}

}
