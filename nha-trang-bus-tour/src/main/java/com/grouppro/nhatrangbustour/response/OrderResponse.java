package com.grouppro.nhatrangbustour.response;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderResponse {

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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    private Long orderId;
    private LocalDate orderDate;
    private Long paymentId;
    private Long userId;

    public OrderResponse(Long orderId, LocalDate orderDate, Long paymentId, Long userId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.paymentId = paymentId;
        this.userId = userId;
    }

    
    // Add getters and setters
}
