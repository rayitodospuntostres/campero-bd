package com.microservices.order_detail.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_order;

    private Long id_product;

    private Long quantity;

    private Long price;

    private Long unite_price;

    private Long sub_total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getUnite_price() {
        return unite_price;
    }

    public void setUnite_price(Long unite_price) {
        this.unite_price = unite_price;
    }

    public Long getSub_total() {
        return sub_total;
    }

    public void setSub_total(Long sub_total) {
        this.sub_total = sub_total;
    }
}
