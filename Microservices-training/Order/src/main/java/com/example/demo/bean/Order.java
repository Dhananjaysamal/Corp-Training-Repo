package com.example.demo.bean;

public class Order {
	
	private int orderId;
	private String orderDesc;
	
	public Order(){
		
	}
	
	public  Order(int orderId,String orderDesc) {
		this.orderId=orderId;
		this.orderDesc=orderDesc;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	
	
	

}
