package com.example.demo.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dto.AccountDetail;
import com.example.demo.dto.OrderDetail;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.OrderResponse;
import com.example.demo.dto.PaymentRequest;
import com.example.demo.entity.OrderItemEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserOrderEntity;
import com.example.demo.feign.client.BankClient;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.UserRegRepo;

@Service
public class OrderService {
	
	@Autowired
	private UserRegRepo userRegRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private BankClient bankClient;

	public OrderResponse createOrder(OrderRequestDto orderRequestDto, int userId, String vendor,String cardNumber, int cvv, int month,int year,double amount,long toAccount) throws ParseException {

		OrderResponse orderResponse=null;
		Optional<UserEntity> userEntity = userRegRepo.findById(userId);
		if(userEntity.isPresent()) {
			UserEntity user = userEntity.get();
			List<OrderItemDto> orderRequestDtos= orderRequestDto.getOrderItemRequests();
			if(!orderRequestDtos.isEmpty()) {

				UserOrderEntity userOrder=new UserOrderEntity();
				userOrder.setOrderCreated(new Date());
				userOrder.setUserEntity(userEntity.get());
				List<OrderItemEntity> orderItems = createOrderItemsFromOrderRequest(orderRequestDtos,userOrder);

				BigDecimal totalPrice = orderItems.stream()
						.map(item -> {
							BigDecimal quantity= BigDecimal.valueOf(item.getQuantity());
							return  quantity.multiply(item.getPrice());
						})
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				userOrder.setTotalPrice(totalPrice);
				userOrder.setQuantity(orderItems.size());
				userOrder.setOrderItems(orderItems.stream().collect(Collectors.toSet()));
				userOrder.setOrderId(generateUniqueOrderId());
				RedirectView paymentStatus = makePayment( cardNumber,  cvv,  month, year, amount, toAccount);
				userOrder.setPaymentId(paymentStatus.RESPONSE_STATUS_ATTRIBUTE);
				UserOrderEntity order = orderRepo.save(userOrder);
				orderResponse=createOrderDetail(order);
			} else {

				throw new RuntimeException("user must select one or more menu item");
			}

		} else  {
			throw new RuntimeException("user not found");
		}
		return orderResponse;
	}

	private OrderResponse createOrderDetail(UserOrderEntity order) {

		List<OrderItemEntity> orderItems = order.getOrderItems().stream().collect(Collectors.toList());
		List<OrderItemDto> orderItemsToDtos = convertOrderItemsToDtos(orderItems);
		OrderResponse orderResponse=new OrderResponse();
		Map<OrderDetail, List<OrderItemDto>> orderResposeMap=new HashMap<>();
		orderResposeMap.put(mapOrderToOrderDetail(order), orderItemsToDtos);
		orderResponse.setMap(orderResposeMap);
		return orderResponse;
	}

	private OrderDetail mapOrderToOrderDetail(UserOrderEntity order) {
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrderId(order.getOrderId());
		orderDetail.setQuantity(order.getQuantity());
		orderDetail.setTotalPrice(order.getTotalPrice());
		orderDetail.setPaymentId(order.getPaymentId());
		orderDetail.setOrderCreated(order.getOrderCreated());
		return orderDetail;
	}

	private RedirectView makePayment(String cardNumber, int cvv, int month,int year,double amount,long toAccount) throws ParseException {

//		AccountDetail accountDetails = bankClient.getAccountDetails(userId);
//		PaymentRequest paymentRequest=new PaymentRequest();
//		paymentRequest.setAmount(amount);
//		paymentRequest.setCardNumber(accountDetails.getCardNumber());
//		paymentRequest.setCvv(accountDetails.getCvv());
//		paymentRequest.setExpirationDate(accountDetails.getExpirationDate());
		return bankClient.payment(null, cardNumber, cvv, month, year, amount, toAccount);

	}

	private List<OrderItemEntity> createOrderItemsFromOrderRequest(List<OrderItemDto> orderRequestDtos, UserOrderEntity userOrder) {
		List<OrderItemEntity> orderItems = orderRequestDtos.stream()
		.map(orderRequest -> {
			OrderItemEntity orderItem=new OrderItemEntity();
			orderItem.setItemName(orderRequest.getName());
			orderItem.setOrderItemId(generateUniqueOrderItemId());
			orderItem.setPrice(orderRequest.getPrice());
			orderItem.setQuantity(orderRequest.getQuantity());
			orderItem.setUserOrderEntity(userOrder);
			return orderItem;
		}).collect(Collectors.toList());
		return orderItems;
	}

	private String generateUniqueOrderItemId() {
		int unique = 100 + new Random().nextInt(900);	
		String orderItemId="item"+unique;
		return orderItemId;
	}

	private String generateUniqueOrderId() {
		int unique = 1000 + new Random().nextInt(9000);	
		String orderId="order"+unique;
		return orderId;
	}

	public OrderResponse getOrderHistory(int userId) {
		OrderResponse orderResponse=new OrderResponse();
		if(userRegRepo.findById(userId).isPresent()) {
			UserEntity userEntity = userRegRepo.findById(userId).get();
			List<UserOrderEntity> orders = userEntity.getUserOrders().stream().collect(Collectors.toList()).subList(0, 2);

			Map<OrderDetail, List<OrderItemDto>> orderResposeMap = createOrderResposeMap(orders);
			orderResponse.setMap(orderResposeMap);
		} 
		return orderResponse;
	}

	private Map<OrderDetail, List<OrderItemDto>> createOrderResposeMap(List<UserOrderEntity> orders) {

		Map<OrderDetail, List<OrderItemDto>> orderResposeMap=new HashMap<>();

		orders.stream().forEach(
				order -> {
					List<OrderItemEntity> orderItems = order.getOrderItems().stream().collect(Collectors.toList());
					List<OrderItemDto> orderItemsDtos= convertOrderItemsToDtos(orderItems);
					OrderDetail orderDetail=new OrderDetail();
					orderDetail.setOrderId(order.getOrderId());
					orderDetail.setQuantity(order.getQuantity());
					orderDetail.setTotalPrice(order.getTotalPrice());
					orderDetail.setPaymentId(order.getPaymentId());
					orderDetail.setOrderCreated(order.getOrderCreated());
					orderResposeMap.put(orderDetail, orderItemsDtos);
				});

		return orderResposeMap;
	}



	private List<OrderItemDto> convertOrderItemsToDtos(List<OrderItemEntity> orderItems) {
		List<OrderItemDto> orderItemsDtos=null;
		if(orderItems.isEmpty()) {
			orderItemsDtos=new ArrayList<>();
		} else {
			orderItemsDtos=orderItems.stream().map(orderItem ->{ 
				OrderItemDto orderItemDto=new OrderItemDto();
				orderItemDto.setName(orderItem.getItemName());
				orderItemDto.setQuantity(orderItem.getQuantity());
				orderItemDto.setPrice(orderItem.getPrice());
				return orderItemDto;
			}).collect(Collectors.toList());
		}

		return orderItemsDtos;
	}

}
