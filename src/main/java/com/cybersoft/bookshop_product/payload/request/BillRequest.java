package com.cybersoft.bookshop_product.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequest {
    private String cmnd;
    private String phone;
    private String address;
    private String cardNumber;
    private String ccv;
}
