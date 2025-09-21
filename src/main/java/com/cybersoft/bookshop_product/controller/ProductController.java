package com.cybersoft.bookshop_product.controller;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;
import com.cybersoft.bookshop_product.payload.request.SearchProductRequest;
import com.cybersoft.bookshop_product.payload.response.BaseResponse;
import com.cybersoft.bookshop_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getListProduct() {
        BaseResponse response = new BaseResponse();
        List<ProductDTO> listProduct = productService.getAllProducts();
        response.setData(listProduct);
        response.setMessage("Get list product success");
        response.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts(SearchProductRequest request) {
        BaseResponse response = new BaseResponse();
        response.setData(productService.searchProducts(request).getContent());
        response.setMessage("Search products success");
        response.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        BaseResponse response = new BaseResponse();
        ProductDTO createdProduct = productService.createProduct(request);
        response.setData(createdProduct);
        response.setMessage("Create product success");
        response.setCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}