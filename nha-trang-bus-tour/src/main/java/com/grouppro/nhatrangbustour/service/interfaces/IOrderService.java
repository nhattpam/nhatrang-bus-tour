package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();
    List<Order> getOrderById(Long id);
}
