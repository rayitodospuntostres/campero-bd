package com.microservices.payment.services;

import com.microservices.payment.models.Payment;

import java.util.List;

public interface PaymentService {
    Payment save(Payment payment);

    Payment findById(Long id);

    void deleteById(Long id);

    List<Payment> findAll();
}
