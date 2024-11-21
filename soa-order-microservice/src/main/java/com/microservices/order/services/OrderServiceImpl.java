package com.microservices.order.services;

import com.microservices.order.models.Order;
import com.microservices.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        if(order != null){
            orderRepository.delete(order);
        }
    }

    @Override
    public void updateOrder(Order order) {

            Order order1 = getOrderById(order.getId());
            if(order1 != null){
                order1.setId_user(order.getId_user());
                order1.setDate_order(order.getDate_order());
                order1.setTotal(order.getTotal());
                order1.setStatus(order.getStatus());

                orderRepository.save(order1);
            }
    }

}
