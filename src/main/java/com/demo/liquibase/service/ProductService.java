package com.demo.liquibase.service;

import com.demo.liquibase.model.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    public Product getProduct(int id) {
        // Get the product in database.
        return new Product();
    }

    public Product addProducts(Product product) {
        // Add in database.
        return new Product();
    }

    public Product updateProducts(int sku, Product product) {
        // Update in database.
        return new Product();
    }
}
