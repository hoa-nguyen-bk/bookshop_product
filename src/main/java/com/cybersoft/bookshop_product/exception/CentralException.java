package com.cybersoft.bookshop_product.exception;

import com.cybersoft.bookshop_product.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralException {
    @ExceptionHandler()
    public ResponseEntity<BaseResponse> handleProductNotFound(Exception e) {
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
