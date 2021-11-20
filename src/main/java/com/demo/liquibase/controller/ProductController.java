package com.demo.liquibase.controller;

import com.demo.liquibase.model.Product;
import com.demo.liquibase.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProducts(
            @Validated @RequestBody Product request
    ) {
        Product product = productService.addProducts(request);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(product);
    }


    @PutMapping("/products/{sku}")
    public ResponseEntity<Product> updateProducts(
            @PathVariable("sku") int sku,
            @Validated @RequestBody Product request
    ) {
        Product product = productService.updateProducts(sku, request);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(product);
    }

    @GetMapping("/products/{sku}")
    public ResponseEntity<Product> getProduct(
            @PathVariable("sku") int sku
    ) {
        Product product = productService.getProduct(sku);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(product);
    }
}
