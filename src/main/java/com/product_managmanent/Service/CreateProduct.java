package com.product_managmanent.Service;

import com.product_managmanent.Model.Product;
import com.product_managmanent.Model.ProductDTO;
import com.product_managmanent.repository.ProductRepo;
import com.product_managmanent.Service.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct implements Query<Product, ProductDTO> {

    @Autowired
    ProductRepo repo;

    @Override
    public ResponseEntity<ProductDTO> handle(Product product) {
        Product saved = repo.save(product);
        ProductDTO dto = new ProductDTO(saved.getProd_name(), saved.getProd_price());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
