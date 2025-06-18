package com.product_managmanent.exceptions;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class handler {
    @ExceptionHandler(ErrorClass.class)
    public ResponseEntity<Object> handel(ErrorClass err){
        HashMap<String,Object> body = new HashMap<>() ; 
        body.put("Error : ",err.getMessage()) ; 
        body.put("status",err.getStatus().value()) ; 
        body.put("Descreption : ","Error when do operation") ;
        return ResponseEntity.status(err.getStatus()).body(body) ; 
    }
}   
