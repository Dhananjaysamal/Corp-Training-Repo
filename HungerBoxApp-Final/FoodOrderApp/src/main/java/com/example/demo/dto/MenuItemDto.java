package com.example.demo.dto;

public class MenuItemDto {
	
	  private String VendorId;
	    private String VendorName;

	    private String Desc;

	    private double price;
		
		private String MenuType;
		
		private String ItemName;
		
		private String VendorAddress;
		
		public String getVendorAddress() {
			return VendorAddress;
		}

		public void setVendorAddress(String vendorAddress) {
			VendorAddress = vendorAddress;
		}

		public MenuItemDto() {
			
		}

		public MenuItemDto(String VendorName, String Desc, double price, String MenuType,String ItemName) {
			super();
			this.VendorName = VendorName;
			this.Desc = Desc;
			this.price = price;
			this.MenuType = MenuType;
			this.ItemName=ItemName;
		}

		public String getVendorId() {
			return VendorId;
		}

		public void setVendorId(String vendorId) {
			VendorId = vendorId;
		}

		public String getVendorName() {
			return VendorName;
		}

		public void setVendorName(String vendorName) {
			VendorName = vendorName;
		}

		public String getDesc() {
			return Desc;
		}

		public void setDesc(String desc) {
			Desc = desc;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getMenuType() {
			return MenuType;
		}

		public void setMenuType(String menuType) {
			MenuType = menuType;
		}

		public String getItemName() {
			return ItemName;
		}

		public void setItemName(String itemName) {
			ItemName = itemName;
		}

}
