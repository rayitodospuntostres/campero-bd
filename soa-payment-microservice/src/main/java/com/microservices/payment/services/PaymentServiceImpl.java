package com.microservices.payment.services;

import com.microservices.payment.models.Payment;
import com.microservices.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public Payment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Payment payment = findById(id);
        if(payment != null){
            repository.delete(payment);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        return repository.findAll();
    }
}
