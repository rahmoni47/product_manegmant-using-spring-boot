package com.product_managmanent.Service;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import com.product_managmanent.Model.Product;
import com.product_managmanent.repository.ProductRepo;
@Service
public class GetAllProducts implements Query<Void,List<Product>> {
    @Autowired
    ProductRepo repo ; 
    @Override
    public ResponseEntity<List<Product>> handle (Void input){
        List<Product> prod_list = repo.findAll() ; 
        return ResponseEntity.status(HttpStatus.OK).body(prod_list) ; 
    }
}
