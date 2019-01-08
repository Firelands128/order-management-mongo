package com.wenqi.ordermanagement.dao.entity;

import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.constants.Pair;
import com.wenqi.ordermanagement.dto.OrderDto;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    @Id
    private String id;
    private Date createDatetime;
    private String userId;
    private List<Pair<String, Integer>> items;
    private BigDecimal subtotal;
    private List<Coupon> coupons;
    private BigDecimal save;
    private BigDecimal tax;
    private BigDecimal total;
    private String notes;
    private OrderStatus status;
    private BigDecimal tips;


    public Order() {
    }

    public Order(String id, Date createDatetime, String userId, List<Pair<String, Integer>> items, BigDecimal subtotal, List<Coupon> coupons, BigDecimal save, BigDecimal tax, BigDecimal total, String notes, OrderStatus status, BigDecimal tips) {
        this.id = id;
        this.createDatetime = createDatetime;
        this.userId = userId;
        this.items = items;
        this.subtotal = subtotal;
        this.coupons = coupons;
        this.save = save;
        this.tax = tax;
        this.total = total;
        this.notes = notes;
        this.status = status;
        this.tips = tips;
    }

    public Order(OrderDto orderDto) {
        this.setCreateDatetime(orderDto.createDatetime);
        this.setUserId(orderDto.userId);
        this.setItems(orderDto.items);
        this.setSubtotal(orderDto.subtotal);
        this.setCoupons(orderDto.coupons);
        this.setSave(orderDto.save);
        this.setTax(orderDto.tax);
        this.setTotal(orderDto.total);
        this.setNotes(orderDto.notes);
        this.setStatus(orderDto.status);
        this.setTips(orderDto.tips);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Pair<String, Integer>> getItems() {
        return items;
    }

    public void setItems(List<Pair<String, Integer>> items) {
        this.items = items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public BigDecimal getSave() {
        return save;
    }

    public void setSave(BigDecimal save) {
        this.save = save;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTips() {
        return tips;
    }

    public void setTips(BigDecimal tips) {
        this.tips = tips;
    }
}
