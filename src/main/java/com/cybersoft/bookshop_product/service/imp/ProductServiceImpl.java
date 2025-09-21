package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.dto.ProductDTO;
import com.cybersoft.bookshop_product.entity.Product;
import com.cybersoft.bookshop_product.mapper.ProductMapper;
import com.cybersoft.bookshop_product.payload.request.CreateProductRequest;
import com.cybersoft.bookshop_product.payload.request.SearchProductRequest;
import com.cybersoft.bookshop_product.repository.ProductRepository;
import com.cybersoft.bookshop_product.service.FileStorageServices;
import com.cybersoft.bookshop_product.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private FileStorageServices fileStorageServices;

    @Autowired
    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductDTO createProduct(CreateProductRequest productRequest) {
        StringBuilder images = new StringBuilder();

        String checksum;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(productRequest.getFiles().getBytes());
            checksum = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException("Error calculating file checksum", e);
        }

        if(!checksum.equals(productRequest.getChecksum())) {
            throw new RuntimeException("Checksum does not match");
        }

        fileStorageServices.save(productRequest.getFiles());
        images.append(productRequest.getFiles().getOriginalFilename());

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

    @Override
    public Page<ProductDTO> searchProducts(SearchProductRequest request) {
        Specification<Product> specification = filter(request.getTitle(), request.getAuthor());

        return productRepository.findAll(specification, PageRequest.of(request.getNumPage(), request.getPageSize())).map(ProductMapper::mapToDTO);
    }

    public Specification<Product> filter(String title, String author) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // WHERE (title = "ABC" AND author = "XYZ")

            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}