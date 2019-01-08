package com.wenqi.ordermanagement.dao.repository;

import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.dao.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);

    List<Order> findByUserIdAndStatus(String userId, OrderStatus status);
}
