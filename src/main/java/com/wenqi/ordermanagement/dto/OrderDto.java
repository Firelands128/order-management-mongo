package com.wenqi.ordermanagement.dto;

import com.wenqi.ordermanagement.constants.OrderStatus;
import com.wenqi.ordermanagement.constants.Pair;
import com.wenqi.ordermanagement.dao.entity.Coupon;
import com.wenqi.ordermanagement.dao.entity.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDto {
    public String id;
    public Date createDatetime;
    public String userId;
    public List<Pair<String, Integer>> items;
    public BigDecimal subtotal;
    public List<Coupon> coupons;
    public BigDecimal save;
    public BigDecimal tax;
    public BigDecimal total;
    public String notes;
    public OrderStatus status;
    public BigDecimal tips;

    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.createDatetime = order.getCreateDatetime();
        this.userId = order.getUserId();
        this.items = order.getItems();
        this.subtotal = order.getSubtotal();
        this.coupons = order.getCoupons();
        this.save = order.getSave();
        this.tax = order.getTax();
        this.total = order.getTotal();
        this.notes = order.getNotes();
        this.status = order.getStatus();
        this.tips = order.getTips();
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id='" + id + '\'' +
                ", createDatetime=" + createDatetime +
                ", userId='" + userId + '\'' +
                ", items=" + items +
                ", subtotal=" + subtotal +
                ", coupons=" + coupons +
                ", save=" + save +
                ", tax=" + tax +
                ", total=" + total +
                ", notes='" + notes + '\'' +
                ", status=" + status +
                ", tips=" + tips +
                '}';
    }
}
