package com.microservices.payment.controller;


import com.microservices.payment.models.Payment;
import com.microservices.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public Payment save(@RequestBody Payment payment){
        return paymentService.save(payment);
    }

    @GetMapping("/find/{id}")
    public Payment findById(@PathVariable Long id){
        return paymentService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Payment> findAll(){
        return paymentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        paymentService.deleteById(id);
    }


    @GetMapping("/example")
    public String example(){
        return "Payment Microservice is working";
    }

}
