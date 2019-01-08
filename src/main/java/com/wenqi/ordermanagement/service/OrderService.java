package com.wenqi.ordermanagement.service;

import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<OrderDto> addOrder(OrderDto orderDto);

    Optional<OrderDto> getOrderById(String id);

    List<OrderDto> getOrderByUserId(String userId);

    List<OrderDto> getOrderByUserIdAndStatus(String userId, OrderStatus status);

    void updateOrderStatus(String orderId, OrderStatus status);
}
