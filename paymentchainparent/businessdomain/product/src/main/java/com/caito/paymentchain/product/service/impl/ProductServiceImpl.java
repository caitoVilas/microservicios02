package com.caito.paymentchain.product.service.impl;

import com.caito.paymentchain.product.entity.Product;
import com.caito.paymentchain.product.repository.ProductRepository;
import com.caito.paymentchain.product.service.contract.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author caito Vilas
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product save(Product product) {
        log.info("iniciando servicio guardar producto");
        log.info("guardando producto...");
        return productRepository.save(product);
    }

    @Override
    public Product getOne(Long id) {
        log.info("iniciando servicio buscar producto por id");
        log.info("buscando producto...");
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    @Override
    public List<Product> getAll() {
        log.info("iniciando servicio buscar productos");
        log.info("buscando productos...");
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        log.info("iniciando servicio eliminar producto");
        log.info("eliminando producto...");
        Product product = productRepository.findById(id).orElse(null);
        if (product != null)
            productRepository.deleteById(id);
    }
}
