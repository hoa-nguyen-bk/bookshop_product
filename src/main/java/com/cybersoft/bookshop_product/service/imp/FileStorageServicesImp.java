package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.service.FileStorageServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServicesImp implements FileStorageServices {

    @Value("${file.path}")
    private String root;

    @Override
    public void save(MultipartFile file) {
        // ĐƯờng dẫn lưu file ko được dán cứng ở đây mà phải lưu trong application.yml
        try {
            Path rootPath = Paths.get(root);
            if(!Files.exists(rootPath)) {
                Files.createDirectory(rootPath);
            }
            Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new RuntimeException("Could not save file", e);
        }
    }

    @Override
    public Resource load(String fileName) {
        // Logic to load the file as a Resource
        return null; // Placeholder return statement
    }
}
