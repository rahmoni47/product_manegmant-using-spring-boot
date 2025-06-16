package com.product_managmanent.Controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.product_managmanent.Model.Product;
import com.product_managmanent.Model.ProductDTO;
import com.product_managmanent.Model.UpdateProd;
import com.product_managmanent.Service.CreateProduct;
import com.product_managmanent.Service.DeleteProduct;
import com.product_managmanent.Service.UpdateProduct;

import jakarta.websocket.server.PathParam;

import com.product_managmanent.Service.GetAllProducts;


import org.hibernate.sql.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/product")
public class ProductController {
    private CreateProduct create; 
    private DeleteProduct delete ; 
    private GetAllProducts getall ; 
    private UpdateProduct update ; 
    
    public ProductController(CreateProduct create, DeleteProduct delete, GetAllProducts getall, UpdateProduct update) {
        this.create = create;
        this.delete = delete;
        this.getall = getall;
        this.update = update;
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return getall.handle(null) ; 
    }
    @PostMapping("/")
    public ResponseEntity<ProductDTO> create(@RequestBody Product entity) {
       return create.handle(entity) ;  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return delete.handle(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody Product product) {
        return update.handle(new UpdateProd(id, product));
    }

}
