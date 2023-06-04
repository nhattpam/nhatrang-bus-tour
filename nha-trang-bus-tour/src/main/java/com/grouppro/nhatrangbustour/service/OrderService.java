package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.OrderRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
}
