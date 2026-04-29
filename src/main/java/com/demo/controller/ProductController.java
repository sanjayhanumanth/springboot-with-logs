package com.demo.controller;

import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    // 1️⃣ Create Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("Received request to create product: {}", product.getName());

        Product savedProduct = productRepository.save(product);

        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    // 2️⃣ Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Fetching all products");

        List<Product> products = productRepository.findAll();

        log.debug("Total products found: {}", products.size());
        return products;
    }

    // 3️⃣ Get Product by ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        log.info("Fetching product with ID: {}", id);

        return productRepository.findById(id)
                .map(product -> {
                    log.debug("Product found: {}", product.getName());
                    return product;
                })
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });
    }
}