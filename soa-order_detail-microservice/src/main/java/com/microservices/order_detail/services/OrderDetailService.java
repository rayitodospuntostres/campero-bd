package com.microservices.order_detail.services;


import com.microservices.order_detail.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    public OrderDetail saveOrderDetail(OrderDetail orderDetail);
    public List<OrderDetail> getOrderDetails();
    public OrderDetail getOrderDetailById(Long id);
    public void deleteOrderDetail(Long id);
    public void updateOrderDetail(OrderDetail orderDetail);
}
