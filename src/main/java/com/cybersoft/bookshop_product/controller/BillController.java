package com.cybersoft.bookshop_product.controller;

import com.cybersoft.bookshop_product.entity.Product;
import com.cybersoft.bookshop_product.payload.request.BillRequest;
import com.cybersoft.bookshop_product.utils.SignatureUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;

@RestController
@RequestMapping("/bill")
public class BillController {
    @PostMapping
    public ResponseEntity<?> bill(@RequestBody String enscyptedData) {
        BillRequest billRequest = new BillRequest();
        try {
            byte[] data = Base64.getDecoder().decode(enscyptedData);

            PrivateKey privatekey = SignatureUtils.loadPrivateKeyFromResource("private_deccript_generate_key.pem", "SHA256withRSA");
            byte[] descrypted = SignatureUtils.rsaDecryptOaep(privatekey, data);
            String dataJson = new String(descrypted, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            billRequest = objectMapper.readValue(dataJson, BillRequest.class);

            System.out.println("Data json: " + billRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(billRequest);
    }
}
