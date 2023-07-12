package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.User;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();
    List<Order> getOrdersByUser(User user);
    Long saveOrder(Order order, Long pid, Long uid);
    Long editOrder(Order order);
    Order getOrderById(Long oid);
}
