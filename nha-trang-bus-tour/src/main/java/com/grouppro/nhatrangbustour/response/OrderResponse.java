package com.grouppro.nhatrangbustour.response;

import com.grouppro.nhatrangbustour.Entity.Payment;

import java.time.LocalDate;

public class OrderResponse {

    private Long orderId;
    private LocalDate orderDate;
    private Payment payment;
    private Long userId;

    public OrderResponse(Long orderId, LocalDate orderDate, Payment payment, Long userId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.payment = payment;
        this.userId = userId;
    }

    
    // Add getters and setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
