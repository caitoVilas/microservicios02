package com.caito.paymentchain.product.service.contract;

import com.caito.paymentchain.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product getOne(Long id);
    List<Product> getAll();
    void delete(Long id);
}
