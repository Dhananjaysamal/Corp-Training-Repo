package com.example.demo.batch;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.dto.MenuItemDto;

@Component
public class ProcessData implements ItemProcessor<MenuItemDto, MenuItemDto> {
	
	public  ProcessData() {
		
	}

	@Override
	public MenuItemDto process(MenuItemDto menuItemDto) throws Exception {
	
		  List<String> list = Arrays.stream(menuItemDto.getDesc().split(","))
		    		.map(desc -> desc.toUpperCase()).collect(Collectors.toList());
		String description = String.join(", ", list);
		menuItemDto.setMenuType(menuItemDto.getMenuType().toUpperCase());
	    menuItemDto.setDesc(description);
		return menuItemDto;
	}
	
}
