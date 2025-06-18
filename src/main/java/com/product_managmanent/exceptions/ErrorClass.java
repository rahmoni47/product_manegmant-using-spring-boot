package com.product_managmanent.exceptions;

import org.springframework.http.HttpStatus;

import com.product_managmanent.enums.RuntimeErrors;

public class ErrorClass extends RuntimeException{
    private final HttpStatus status;

    public ErrorClass(RuntimeErrors err,HttpStatus status){
        super(err.getmessage()) ; 
        this.status = status ; 
    }
    public HttpStatus getStatus() {
        return status;
    }
}
