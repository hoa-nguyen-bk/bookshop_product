package com.cybersoft.bookshop_product.repository;

import com.cybersoft.bookshop_product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
