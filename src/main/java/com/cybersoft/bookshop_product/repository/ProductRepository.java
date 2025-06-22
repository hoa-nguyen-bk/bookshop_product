package com.cybersoft.bookshop_product.repository;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
}
