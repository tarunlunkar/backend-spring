package com.example.miniproject.Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class OutofStockException extends RuntimeException{
    public OutofStockException(String message) {
        super(message);
    }
}
