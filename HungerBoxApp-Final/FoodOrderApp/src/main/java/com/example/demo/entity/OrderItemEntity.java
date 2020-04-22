package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItemEntity {
	
	@Id
	@Column(name="order_item_id",nullable = false,columnDefinition="VARCHAR(64)")
	private String orderItemId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="item_name")
	private String itemName;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="orderid",nullable = false)
	private UserOrderEntity userOrderEntity;
	
	public OrderItemEntity() {
		
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public UserOrderEntity getUserOrderEntity() {
		return userOrderEntity;
	}

	public void setUserOrderEntity(UserOrderEntity userOrderEntity) {
		this.userOrderEntity = userOrderEntity;
	}

}
