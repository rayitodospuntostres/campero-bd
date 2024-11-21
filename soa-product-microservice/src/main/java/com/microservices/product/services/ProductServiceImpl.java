package com.microservices.product.services;

import com.microservices.product.model.Product;
import com.microservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;


    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findById(id);
        if (existingProduct != null) {
            // Actualiza los campos del producto existente
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
            // Si hay más campos, agrégalos aquí
            return repository.save(existingProduct); // Guarda los cambios en la base de datos
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }


    @Override
    public void deleteById(Long id) {
        Product product = findById(id);
        if(product != null){
            repository.delete(product);
        }
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }


}
