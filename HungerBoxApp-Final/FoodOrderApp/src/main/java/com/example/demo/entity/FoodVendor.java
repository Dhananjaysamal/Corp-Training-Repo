package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")
public class FoodVendor {
	
	@Id
	@Column(name="restaurant_id", nullable = false,columnDefinition="VARCHAR(64)")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
//	@OneToMany(mappedBy = "foodVendor",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@OneToMany(mappedBy = "foodVendor" , fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = MenuItemEntity.class)
//	@JsonIgnore
	private Set<MenuItemEntity> menuItems = new HashSet<MenuItemEntity>();
	
	
	public FoodVendor() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<MenuItemEntity> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set<MenuItemEntity> menuItems) {
		this.menuItems = menuItems;
	}
	
}
