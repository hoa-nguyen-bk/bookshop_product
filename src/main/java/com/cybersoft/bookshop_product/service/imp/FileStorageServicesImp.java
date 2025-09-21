package com.cybersoft.bookshop_product.service.imp;

import com.cybersoft.bookshop_product.exception.FileStorageException;
import com.cybersoft.bookshop_product.service.FileStorageServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

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
            Files.copy(file.getInputStream(), rootPath.resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new FileStorageException("Could not save file"+ file.getOriginalFilename() + " because: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String fileName) {
        // Logic to load the file as a Resource
        return null; // Placeholder return statement
    }

    @Override
    public void save(String fileName, byte[] data) {
        // Logic to save the byte array as a file

        File file = new File(root + "/" +fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }
         catch (Exception e) {
            throw new FileStorageException("Could not save file " + fileName + " because: " + e.getMessage());
        }
    }
}
