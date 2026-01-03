package com.ecommerce.styledup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.styledup.model.Product;
import com.ecommerce.styledup.repository.ProductRepository;



@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // method to save/insert a product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductByID(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

   
}
