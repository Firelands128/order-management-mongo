package com.wenqi.ordermanagement.service.impl;

import com.wenqi.ordermanagement.constants.Constant;
import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.constants.Pair;
import com.wenqi.ordermanagement.dao.entity.Coupon;
import com.wenqi.ordermanagement.dao.entity.Order;
import com.wenqi.ordermanagement.dao.repository.OrderRepository;
import com.wenqi.ordermanagement.dto.OrderDto;
import com.wenqi.ordermanagement.exception.ErrorCode;
import com.wenqi.ordermanagement.exception.IOrderException;
import com.wenqi.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderDto> addOrder(OrderDto orderDto) {
        orderDto.createDatetime = new Date();
        orderDto.status = OrderStatus.UNPAID;
        this.checkOrderDtoAvailable(orderDto);
        Order addedOrder = orderRepository.insert(new Order(orderDto));
        return Optional.of(new OrderDto(addedOrder));
    }

    @Override
    public Optional<OrderDto> getOrderById(String id) {
        Optional<Order> found = orderRepository.findById(id);
        if (found.isPresent()) {
            return Optional.of(new OrderDto(found.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDto> getOrderByUserId(String userId) {
        List<Order> found = orderRepository.findByUserId(userId);
        List<OrderDto> result = new ArrayList<>(found.size());
        if (found.size() > 0) {
            found.forEach(order -> result.add(new OrderDto(order)));
        }
        return result;
    }

    @Override
    public List<OrderDto> getOrderByUserIdAndStatus(String userId, OrderStatus status) {
        List<Order> found = orderRepository.findByUserIdAndStatus(userId, status);
        List<OrderDto> result = new ArrayList<>(found.size());
        if (found.size() > 0) {
            found.forEach(order -> result.add(new OrderDto(order)));
        }
        return result;
    }

    @Override
    public void updateOrderStatus(String orderId, OrderStatus status) {
        Optional<Order> found = orderRepository.findById(orderId);
        if (found.isPresent()) {
            Order order = found.get();
            order.setStatus(status);
            orderRepository.save(order);
        } else {
            throw new IOrderException(ErrorCode.BAD_REQUEST, "order doesn't exists by order id: " + orderId);
        }
    }

    private void checkOrderDtoAvailable(OrderDto orderDto) {
        BigDecimal subtotal = new BigDecimal(0);
        for (Pair<String, Integer> pair : orderDto.items) {
            String itemId = pair.getKey();
            //TODO check item price;
            BigDecimal price = new BigDecimal(0);
            BigDecimal quantity = new BigDecimal(pair.getValue());
            subtotal = subtotal.add(price.multiply(quantity));
        }
        if (!orderDto.subtotal.equals(subtotal)) {
            throw new IOrderException(ErrorCode.CONFLICT, "Given subtotal amount is incorrect.");
        }
        double couponValue = 0;
        if (null != orderDto.coupons) {
            for (Coupon coupon : orderDto.coupons) {
                Date expireDate = coupon.getExpireDate();
                if (expireDate.before(orderDto.createDatetime)) {
                    throw new IOrderException(ErrorCode.FORBIDDEN, "Have an expired coupon: " + coupon);
                }
                double threshold = coupon.getThreshold();
                if (threshold > orderDto.subtotal.doubleValue()) {
                    throw new IOrderException(ErrorCode.FORBIDDEN, "Failure to meet threshold of coupon: " + coupon);
                }
                couponValue += coupon.getValue();
            }
            if (couponValue != orderDto.save.doubleValue()) {
                throw new IOrderException(ErrorCode.CONFLICT, "Given coupon saving amount is incorrect.");
            }
        }
        if (orderDto.tax.compareTo(orderDto.subtotal.subtract(orderDto.save).multiply(Constant.TAX_RATE)) != 0) {
            throw new IOrderException(ErrorCode.CONFLICT, "Given tax amount is incorrect.");
        }
        if (orderDto.total.compareTo(orderDto.subtotal.subtract(orderDto.save).add(orderDto.tax)) != 0) {
            throw new IOrderException(ErrorCode.CONFLICT, "Given order total amount is incorrect.");
        }
    }
}
