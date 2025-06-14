package com.cybersoft.bookshop_product.service;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO createProduct( CreateProductRequest createProductRequest);
    ProductDTO updateProduct(String id, ProductDTO productDTO);
    void deleteProduct(String id);
    ProductDTO getProductById(String id);
    List<ProductDTO> getAllProducts();
}
