package com.wenqi.ordermanagement.controller;

import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.dto.OrderDto;
import com.wenqi.ordermanagement.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Transactional
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Add a new order by POSTing order DTO and
     * return a {@link ResponseEntity} of the added order DTO.
     * <p>
     * URL path: "/rest/order/".
     * <p>
     * HTTP method: POST.
     * </p>
     *
     * @param orderDto new order DTO.
     * @return a responseEntity of the added order DTO.
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<OrderDto> addNewOrder(@RequestBody OrderDto orderDto) {
        logger.info("add order: " + orderDto);
        Optional<OrderDto> addedOrderDto = orderService.addOrder(orderDto);
        if (addedOrderDto.isPresent()) {
            logger.info("added order for id: " + orderDto.id + ", order is: " + addedOrderDto);
            return new ResponseEntity<>(addedOrderDto.get(), HttpStatus.CREATED);
        } else {
            logger.info("add order failed.");
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Query and order by giving order id and
     * return a {@link ResponseEntity} of the found order DTO.
     * <p>
     * URL path: "/rest/order/{orderId}".
     * <p>
     * HTTP method: GET.
     * </p>
     *
     * @param orderId order id need to query.
     * @return a responseEntity of the found order DTO.
     */
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String orderId) {
        logger.info("get order by id: " + orderId);
        Optional<OrderDto> found = orderService.getOrderById(orderId);
        if (found.isPresent()) {
            logger.info("got order: " + found.get());
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        } else {
            logger.info("order doesn't exist by id: " + orderId);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Query orders by giving user id and
     * return a {@link ResponseEntity} of the list of order DTOs.
     * <p>
     * URL path: "/rest/order/user/{userId}".
     * <p>
     * HTTP method: GET.
     * </p>
     *
     * @param userId user id used to filter orders.
     * @return a responseEntity of the list of found order DTO.
     */
    @RequestMapping(value = "/order/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable String userId) {
        logger.info("get order by user id: " + userId);
        List<OrderDto> found = orderService.getOrderByUserId(userId);
        if (found.size() > 0) {
            logger.info("got number of orders: " + found.size());
            return new ResponseEntity<>(found, HttpStatus.OK);
        } else {
            logger.info("order doesn't exist by user id: " + userId);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Query orders by giving user id and order status and
     * return a {@link ResponseEntity} of list of order DTOs.
     * <p>
     * URL path: "/rest/order/user/{userId}/status/{status}".
     * <p>
     * HTTP method: GET.
     * </p>
     *
     * @param userId user id used to filter orders.
     * @param status order status used to filter orders.
     * @return a responseEntity of the list of found order DTO.
     */
    @RequestMapping(value = "/order/user/{userId}/status/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDto>> getOrderByUserIdAndOrderStatus(@PathVariable("userId") String userId,
                                                                         @PathVariable("status") OrderStatus status) {
        logger.info("get order by user id: " + userId + " and order status: " + status);
        List<OrderDto> found = orderService.getOrderByUserIdAndStatus(userId, status);
        if (found.size() > 0) {
            logger.info("got number of orders: " + found.size());
            return new ResponseEntity<>(found, HttpStatus.OK);
        } else {
            logger.info("order doesn't exist by user id: " + userId + " and order status: " + status);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an order status by giving order id and order status and
     * return a {@link ResponseEntity} with an empty response body.
     * <p>
     * URL path: "/rest/order/{userId}".
     * <p>
     * HTTP method: PUT.
     * </p>
     *
     * @param orderId order id need to update.
     * @param status  order status need to be set.
     * @return a responseEntity with an empty response body.
     */
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity updateOrderStatus(@PathVariable("orderId") String orderId,
                                            @Param("status") OrderStatus status) {
        logger.info("update order status to PAID by order id: " + orderId);
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok().build();
    }
}
