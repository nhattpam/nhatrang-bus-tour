package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.repository.OrderRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrderById(Long id) {
        return orderRepository.findAllById(Collections.singleton(id));
    }
}
