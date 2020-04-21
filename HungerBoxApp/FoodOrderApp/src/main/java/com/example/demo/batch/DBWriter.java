package com.example.demo.batch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.MenuItemDto;
import com.example.demo.entity.FoodVendor;
import com.example.demo.entity.MenuItemEntity;
import com.example.demo.repository.FoodVendorRepo;

@Component
public class DBWriter implements ItemWriter<MenuItemDto>{
	
	@Autowired
	FoodVendorRepo vendorRepo;

	@Override
	public void write(List<? extends MenuItemDto> menuItemsDtos) throws Exception {
				
		String vendorId = menuItemsDtos.get(0).getVendorId();
		Set<MenuItemEntity> menuItemEntities= new HashSet<MenuItemEntity>();
		
		List<FoodVendor> vendors = new ArrayList<FoodVendor>();
		
		menuItemsDtos.stream().forEach(dto ->{
			MenuItemEntity menuItemEntity = new MenuItemEntity();
			FoodVendor foodVendor = new FoodVendor();
			
			foodVendor.setId(dto.getVendorId());
			foodVendor.setLocation(dto.getVendorAddress());
			foodVendor.setName(dto.getVendorName());
			
			menuItemEntity.setDesc(dto.getDesc());
			menuItemEntity.setItemName(dto.getItemName());
			menuItemEntity.setMenuItemId(generateUniqueMenuId());
			menuItemEntity.setMenuType(dto.getMenuType());
			menuItemEntity.setPrice(dto.getPrice());
			menuItemEntity.setFoodVendor(foodVendor);
			
			vendors.add(foodVendor);
			
			menuItemEntities.add(menuItemEntity);
		});
		vendors.stream().forEach(vendor -> {
			vendor.setMenuItems(menuItemEntities);
		vendorRepo.save(vendor);
		});
	}

	private Set<MenuItemEntity> createMenuItem(List<? extends MenuItemDto> menuItemsDtos, FoodVendor foodVendor) {
		
		Set<MenuItemEntity> meItems = menuItemsDtos.stream().map(itemDto -> {
			MenuItemEntity item=new MenuItemEntity();
			item.setFoodVendor(foodVendor);
			item.setDesc(itemDto.getDesc());
			item.setMenuItemId(generateUniqueMenuId());
			item.setItemName(itemDto.getItemName());
			item.setPrice(itemDto.getPrice());
			item.setMenuType(itemDto.getMenuType());
			return item;
			
		}).collect(Collectors.toSet());
		
		return meItems;
	}

	private String generateUniqueMenuId() {
		int unique = 100 + new Random().nextInt(900);	
		String menuId="M"+unique;
		return menuId;
	}
}
