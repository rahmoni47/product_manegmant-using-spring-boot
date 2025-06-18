package com.product_managmanent.enums;

public enum RuntimeErrors {
    NOT_FOUND("Product not found") ,
    ALREDY_EXIST("product already exist") ;  
    private String message ;

    private RuntimeErrors(String message) {
        this.message = message;
    } 
    public String getmessage() {
        return message;
    }
}
