package com.product_managmanent.Service;

import org.springframework.http.ResponseEntity;

public interface Query<I, O> {
    ResponseEntity<O> handle(I input);
}

