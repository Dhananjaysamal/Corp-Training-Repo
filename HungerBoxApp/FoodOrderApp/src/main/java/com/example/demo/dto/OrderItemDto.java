package com.example.demo.dto;

import java.math.BigDecimal;

public class OrderItemDto {
	private String name;

	private int quantity;
	private BigDecimal price;
	public OrderItemDto() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
