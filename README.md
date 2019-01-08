# Order Management Web Service

Simple implementation of order management web service with Spring Boot and MongoDB.

### services list:
```java
public interface OrderService {
    Optional<OrderDto> addOrder(OrderDto orderDto);

    Optional<OrderDto> getOrderById(String id);

    List<OrderDto> getOrderByUserId(String userId);

    List<OrderDto> getOrderByUserIdAndStatus(String userId, OrderStatus status);

    void updateOrderStatus(String orderId, OrderStatus status);
}
```

### Controller Details
**addNewOrder**
```java
@RequestMapping(value="/order", method=POST,
                consumes="application/json;charset=UTF-8")
public org.springframework.http.ResponseEntity<OrderDto> 
            addNewOrder(@RequestBody OrderDto orderDto)
```
Add a new order by POSTing order DTO and return a ResponseEntity of the added order DTO.  
URL path: "/rest/order/".  
HTTP method: POST.  
Parameters:  
orderDto - new order DTO.  
Returns:  
a responseEntity of the added order DTO.  
**getOrderById**
```java
@RequestMapping(value="/order/{orderId}", method=GET)
public org.springframework.http.ResponseEntity<OrderDto> 
            getOrderById(@PathVariable java.lang.String orderId)
```
Query and order by giving order id and return a ResponseEntity of the found order DTO.  
URL path: "/rest/order/{orderId}".  
HTTP method: GET.  
Parameters:  
orderId - order id need to query.  
Returns:  
a responseEntity of the found order DTO.  
**getOrderByUserId**
```java
@RequestMapping(value="/order/user/{userId}", method=GET)
public org.springframework.http.ResponseEntity<java.util.List<OrderDto>>
            getOrderByUserId(@PathVariable java.lang.String userId)

```                                                                                                                                                             
Query orders by giving user id and return a ResponseEntity of the list of order DTOs.  
URL path: "/rest/order/user/{userId}".  
HTTP method: GET.  
Parameters:  
userId - user id used to filter orders.  
Returns:  
a responseEntity of the list of found order DTO.  
**getOrderByUserIdAndOrderStatus**
```java
@RequestMapping(value="/order/user/{userId}/status/{status}",
                method=GET)
public org.springframework.http.ResponseEntity<java.util.List<OrderDto>> 
            getOrderByUserIdAndOrderStatus(@PathVariable(value="userId") java.lang.String userId,
                                           @PathVariable(value="status") OrderStatus status)
```                                                                                                      
Query orders by giving user id and order status and return a ResponseEntity of list of order DTOs.  
URL path: "/rest/order/user/{userId}/status/{status}".  
HTTP method: GET.  
Parameters:  
userId - user id used to filter orders.  
status - order status used to filter orders.  
Returns:  
a responseEntity of the list of found order DTO.  
**updateOrderStatus**
```java
@RequestMapping(value="/order/{orderId}",
                method=PUT)
public org.springframework.http.ResponseEntity 
            updateOrderStatus(@PathVariable(value="orderId") java.lang.String orderId, 
                              @Param(value="status") OrderStatus status)
```
Update an order status by giving order id and order status and return a ResponseEntity with an empty response body.  
URL path: "/rest/order/{userId}".  
HTTP method: PUT.  
Parameters:  
orderId - order id need to update.  
status - order status need to be set.  
Returns:  
a responseEntity with an empty response body.  