package com.cybersoft.bookshop_product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Product {
    @Id
    private String id;
    private String title;
    private String author;
    private int review;
    private double price;
    private String images;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void onCreate() {
        if (this.id == null) {
            this.id = java.util.UUID.randomUUID().toString(); // Tạo ID ngẫu nhiên nếu chưa có
        }
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
