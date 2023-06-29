package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.repository.OrderRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final UserService userService;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Long saveOrder(Order order, Long pid, Long uid) {
        Payment payment = paymentService.getPaymentById(pid);
        User user = userService.getUserById(uid);
        order.setPayment(payment);
        order.setUser(user);
        if(order!=null){
            orderRepository.save(order);
            List<Order> orders = orderRepository.findAllByPayment(payment);
            List<Order> orders1 = orderRepository.findAllByUser(user);
            payment.setOrder(orders);
            user.setOrder(orders1);
            paymentService.savePayment(payment);
            userService.Register(user);
            return order.getOrderId();
        }
        return null;
    }

    @Override
    public Long editOrder(Order order) {
        if(order!=null){
            orderRepository.save(order);
            return order.getOrderId();
        }
        return null;
    }

    @Override
    public Order getOrderById(Long oid) {
        return orderRepository.getReferenceById(oid);
    }
}
