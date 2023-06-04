package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.PaymentRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IPaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
}
