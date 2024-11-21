package com.microservices.product.services;

import com.microservices.product.model.Product;

import java.util.List;

public interface ProductService  {

    Product save(Product product);

    Product findById(Long id);

    void deleteById(Long id);

    List<Product> findAll();

    Product updateProduct(Long id, Product updatedProduct);
}
