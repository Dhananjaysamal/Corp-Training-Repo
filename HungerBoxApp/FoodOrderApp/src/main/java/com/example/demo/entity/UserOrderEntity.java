package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_order")
public class UserOrderEntity {

		@Id
		@Column(name="orderid",nullable = false,columnDefinition="VARCHAR(64)")
		private String orderId;
		
		@Column(name="quantity")
		private int quantity;
		
		@Column(name="totalprice")
		private BigDecimal totalPrice;
		
		@Column(name="paymentid")
		private String paymentId;
		
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name="user_Id",nullable = false)
		private UserEntity userEntity;
		
		@OneToMany(mappedBy = "userOrderEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JsonIgnore
		private Set<OrderItemEntity> orderItems = new HashSet<>();
		
		@Column(name="order_date")
		private Date orderCreated;
		
		public UserOrderEntity() {
			
		}
		
		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public BigDecimal getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getPaymentId() {
			return paymentId;
		}

		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}

		public UserEntity getUserEntity() {
			return userEntity;
		}

		public void setUserEntity(UserEntity userEntity) {
			this.userEntity = userEntity;
		}

		public Date getOrderCreated() {
			return orderCreated;
		}

		public void setOrderCreated(Date orderCreated) {
			this.orderCreated = orderCreated;
		}

		public Set<OrderItemEntity> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(Set<OrderItemEntity> orderItems) {
			this.orderItems = orderItems;
		}
}
