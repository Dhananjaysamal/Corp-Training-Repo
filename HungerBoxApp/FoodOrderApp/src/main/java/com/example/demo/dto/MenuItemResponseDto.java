package com.example.demo.dto;

public class MenuItemResponseDto {
	
	
	  private MenuItemDto itemDto;
	    
	    private String restaurantName;
	    
	    private String restaurantLocation;
		
		public MenuItemResponseDto() {
			
		}

		public MenuItemResponseDto(MenuItemDto itemDto, String restaurantName, String restaurantLocation) {
			super();
			this.itemDto = itemDto;
			this.restaurantName = restaurantName;
			this.restaurantLocation = restaurantLocation;
		}

		public MenuItemDto getItemDto() {
			return itemDto;
		}

		public void setItemDto(MenuItemDto itemDto) {
			this.itemDto = itemDto;
		}

		public String getRestaurantName() {
			return restaurantName;
		}

		public void setRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
		}

		public String getRestaurantLocation() {
			return restaurantLocation;
		}

		public void setRestaurantLocation(String restaurantLocation) {
			this.restaurantLocation = restaurantLocation;
		}
}
