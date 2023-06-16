package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getPayments();
    Long savePayment(Payment payment);
    Payment getPaymentById(Long pid);
}
