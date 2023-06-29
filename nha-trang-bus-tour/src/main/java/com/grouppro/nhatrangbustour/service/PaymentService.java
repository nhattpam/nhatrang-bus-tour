package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.repository.PaymentRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IPaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Long savePayment(Payment payment) {
        if (payment!=null){
            paymentRepository.save(payment);
            return payment.getPaymentId();
        }
        return null;
    }

    @Override
    public Payment getPaymentById(Long pid) {
        return paymentRepository.getReferenceById(pid);
    }
}
