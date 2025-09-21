package com.cybersoft.bookshop_product.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProductRequest {
    private String title;
    private String author;
    private int review;
    private double price;
    private List<FileRequest> files;
}
