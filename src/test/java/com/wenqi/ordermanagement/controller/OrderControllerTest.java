package com.wenqi.ordermanagement.controller;

import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.constants.Pair;
import com.wenqi.ordermanagement.dao.repository.OrderRepository;
import com.wenqi.ordermanagement.dto.OrderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestRestTemplate template;

    private OrderDto setupOrderDto;

    @Before
    public void setup() {
        orderRepository.deleteAll();

        OrderDto orderDto = generateOrderDto();
        ResponseEntity<OrderDto> addedOrderDto = template.postForEntity("/rest/order", orderDto, OrderDto.class);
        assertEquals(HttpStatus.CREATED, addedOrderDto.getStatusCode());
        setupOrderDto = addedOrderDto.getBody();
    }

    @Test
    public void testAddNewOrder() {
        OrderDto orderDto = generateOrderDto();
        ResponseEntity<OrderDto> entity = template.postForEntity("/rest/order", orderDto, OrderDto.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        OrderDto addedOrderDto = entity.getBody();
        assertNotNull(addedOrderDto);
        assertEquals(orderDto.userId, addedOrderDto.userId);
        assertEquals(orderDto.items, addedOrderDto.items);
        assertEquals(OrderStatus.UNPAID, addedOrderDto.status);
    }

    @Test
    public void testGetOrderById() {
        String orderId = setupOrderDto.id;
        ResponseEntity<OrderDto> entity = template.getForEntity("/rest/order/" + orderId, OrderDto.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        OrderDto found = entity.getBody();
        assertNotNull(found);
        assertEquals(setupOrderDto.id, found.id);
        assertEquals(setupOrderDto.userId, found.userId);
        assertEquals(setupOrderDto.items, found.items);
        assertEquals(setupOrderDto.status, found.status);
    }

    @Test
    public void testGetOrderByUserId() {
        String userId = setupOrderDto.userId;
        ResponseEntity<List<OrderDto>> entity = template.exchange("/rest/order/user/" + userId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDto>>() {
                });
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<OrderDto> found = entity.getBody();
        assertNotNull(found);
        found.forEach(orderDto -> assertEquals(userId, orderDto.userId));
    }

    @Test
    public void testGetOrderByUserIdAndOrderStatus() {
        String userId = setupOrderDto.userId;
        OrderStatus status = setupOrderDto.status;
        ResponseEntity<List<OrderDto>> entity = template.exchange("/rest/order/user/" + userId + "/status/" + status,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDto>>() {
                });
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<OrderDto> found = entity.getBody();
        assertNotNull(found);
        found.forEach(orderDto -> {
            assertEquals(userId, orderDto.userId);
            assertEquals(status, orderDto.status);
        });
    }

    @Test
    public void testUpdateOrderStatus() {
        String orderId = setupOrderDto.id;
        template.put("/rest/order/" + orderId + "?status=PAID", null);
        ResponseEntity<OrderDto> entity = template.getForEntity("/rest/order/" + orderId, OrderDto.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        OrderDto found = entity.getBody();
        assertNotNull(found);
        assertEquals(OrderStatus.PAID, found.status);
    }

    private OrderDto generateOrderDto() {
        OrderDto orderDto = new OrderDto();
        Pair<String, Integer> pair = new Pair<>("itemId123", 1);
        orderDto.userId = "userId123";
        orderDto.items = new ArrayList<>();
        orderDto.items.add(pair);

        orderDto.subtotal = new BigDecimal(0);
        orderDto.coupons = null;
        orderDto.save = new BigDecimal(0);
        orderDto.tax = new BigDecimal(0);
        orderDto.total = orderDto.subtotal.subtract(orderDto.save);
        return orderDto;
    }
}
