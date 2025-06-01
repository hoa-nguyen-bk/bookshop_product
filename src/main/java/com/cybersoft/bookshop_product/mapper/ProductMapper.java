package com.cybersoft.bookshop_product.mapper;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;

public class ProductMapper {
    public static Product mapToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setAuthor(dto.getAuthor());
        product.setReview(dto.getReview());
        product.setPrice(dto.getPrice());
        product.setImages(dto.getImages());
        product.setCreatedDate(dto.getCreatedDate());
        product.setUpdatedDate(dto.getUpdatedDate());
        return product;
    }


    public static ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setAuthor(product.getAuthor());
        dto.setReview(product.getReview());
        dto.setPrice(product.getPrice());
        dto.setImages(product.getImages());
        dto.setCreatedDate(product.getCreatedDate());
        dto.setUpdatedDate(product.getUpdatedDate());
        return dto;
    }

}
