package com.cybersoft.bookshop_product.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignatureResponse {
    private String data;
    private String signature;
    private String algorithm;
    private String message;
}
