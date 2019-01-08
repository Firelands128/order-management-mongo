package com.wenqi.ordermanagement.dao.entity;

import com.wenqi.ordermanagement.constants.CouponType;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Coupon {
    @Id
    String id;
    CouponType type;
    double threshold;
    double value;
    Date expireDate;

    public Coupon() {
    }

    public Coupon(String id, CouponType type, double threshold, double value, Date expireDate) {
        this.id = id;
        this.type = type;
        this.threshold = threshold;
        this.value = value;
        this.expireDate = expireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
