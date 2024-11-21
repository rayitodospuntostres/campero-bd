package com.microservices.order.services;

import com.microservices.order.models.Order;

import java.util.List;

public interface OrderService {

    public Order saveOrder(Order order);
    public List<Order> getOrder();
    public Order getOrderById(Long id);
    public void deleteOrder(Long id);
    public void updateOrder(Order order);


}
