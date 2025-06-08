package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;
import com.cybersoft.bookshop_product.mapper.ProductMapper;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;
import com.cybersoft.bookshop_product.repository.ProductRepository;
import com.cybersoft.bookshop_product.service.FileStorageServices;
import com.cybersoft.bookshop_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private FileStorageServices fileStorageServices;

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(CreateProductRequest productRequest) {
        StringBuilder images = new StringBuilder();
        if(productRequest.getFiles()!= null && productRequest.getFiles().length > 0) {
           for(MultipartFile file: productRequest.getFiles()) {
               fileStorageServices.save(file);
               images.append(file.getOriginalFilename()).append(",");
           }
           images.deleteCharAt(images.length() - 1);
        }
        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setAuthor(productRequest.getAuthor());
        product.setReview(productRequest.getReview());
        product.setPrice(productRequest.getPrice());
        product.setImages(images.toString());

        product = productRepository.save(product);
        // Lưu ý: productRepository.save() sẽ trả về đối tượng Product đã được lưu vào CSDL, bao gồm cả ID tự động sinh ra.
        return ProductMapper.mapToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(String id) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToDTO).toList();
        // tự động ánh xạ mapToDTO luôn, cách viết ngắn gọn hơn ít spoiler code hơn,
    }
}