package com.cybersoft.bookshop_product.entity;

/*
 * BTVN: làm cách nào đó tái sử dụng entity cho các project khác nhau
 * */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
