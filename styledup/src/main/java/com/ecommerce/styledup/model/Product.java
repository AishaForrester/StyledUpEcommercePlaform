package com.ecommerce.styledup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;  // Change from javax to jakarta
import jakarta.persistence.Id;     // Change from javax to jakarta
import jakarta.persistence.PrePersist;
import jakarta.persistence.GeneratedValue;  // Change from javax to jakarta
import jakarta.persistence.GenerationType; 


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Product {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Optional: for auto ID generation
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private int stockQuantity;

    private int sellerId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")  // Map to the DB column
    private LocalDateTime createdAt;  // Add this field

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters and setters

    public void setsellerId(int id) {
        this.sellerId = id;
    }
    public int getsellerId() {
        return this.sellerId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {  //admins and sellers can upload images of their products
        this.imageUrl = imageUrl;
    }
}

