package com.product_managmanent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.product_managmanent.Model.Product;
import com.product_managmanent.Model.ProductDTO;
import com.product_managmanent.enums.RuntimeErrors;
import com.product_managmanent.exceptions.ErrorClass;
import com.product_managmanent.repository.ProductRepo;

import jakarta.websocket.server.ServerEndpoint;
@Service
public class DeleteProduct implements Query<Integer,Void>{
    @Autowired ProductRepo repo ; 
    @Override
    public ResponseEntity<Void> handle(Integer id){
        Optional<Product> prod = repo.findById(id) ; 
        if(prod.isPresent()){
            repo.deleteById(id); ; 
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ; 
        }
        throw new ErrorClass(RuntimeErrors.NOT_FOUND,HttpStatus.NOT_FOUND); 
    }
}
