package com.microservices.order_detail.services;

import com.microservices.order_detail.models.OrderDetail;
import com.microservices.order_detail.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return repository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getOrderDetails() {
        return repository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = getOrderDetailById(id);
        if(orderDetail != null){
            repository.delete(orderDetail);
        }
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {

        OrderDetail orderDetail1 = getOrderDetailById(orderDetail.getId());
        if(orderDetail1 != null){
            orderDetail1.setId_order(orderDetail.getId_order());
            orderDetail1.setId_product(orderDetail.getId_product());
            orderDetail1.setQuantity(orderDetail.getQuantity());
            orderDetail1.setSub_total(orderDetail.getSub_total());
            orderDetail1.setPrice(orderDetail.getPrice());
            orderDetail1.setUnite_price(orderDetail.getUnite_price());
            repository.save(orderDetail1);
        }
    }
}
