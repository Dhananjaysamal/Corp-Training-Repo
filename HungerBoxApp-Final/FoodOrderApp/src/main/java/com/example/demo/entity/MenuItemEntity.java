package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MenuItem")
public class MenuItemEntity {
	@Id
	@Column(name = "menu_item_id" , nullable = false,columnDefinition="VARCHAR(64)")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String menuItemId;

	@Column(name = "item_Name")
	private String itemName;

	@Column(name = "menu_desc")
	private String Desc;

	@Column(name = "Price")
	private double Price;

	@Column(name = "menu_type")
	private String menuType;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "restaurantid", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="restaurant_id" , nullable = false )
	private FoodVendor foodVendor;

	public MenuItemEntity() {

	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getDesc() {
		return Desc;
	}


	public void setDesc(String desc) {
		Desc = desc;
	}

	public double getPrice() {
		return Price;
	}


	public void setPrice(double price) {
		Price = price;
	}


	public FoodVendor getFoodVendor() {
		return foodVendor;
	}

	public void setFoodVendor(FoodVendor foodVendor) {
		this.foodVendor = foodVendor;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}
}
