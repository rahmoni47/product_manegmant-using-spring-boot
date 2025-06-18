package com.product_managmanent.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product_managmanent.Model.ProductDTO;
import com.product_managmanent.Model.Product;
import com.product_managmanent.Model.UpdateProd;
import com.product_managmanent.enums.RuntimeErrors;
import com.product_managmanent.exceptions.ErrorClass;
import com.product_managmanent.repository.ProductRepo;
@Service
public class UpdateProduct implements Query<UpdateProd,ProductDTO>{
    @Autowired ProductRepo repo ; 

    @Override
    public ResponseEntity<ProductDTO> handle( UpdateProd prod_info){
        Optional<Product> prod = repo.findById(prod_info.getNew_id()) ; 
        if(prod.isPresent()){
            Product product = prod.get() ;
            product.setProd_id(prod_info.getNew_id());
            product.setProd_name(prod_info.getDto().getProd_name());
            product.setProd_price(prod_info.getDto().getProd_price()); 
            repo.save(product) ; 
            ProductDTO dto = new ProductDTO(product.getProd_name(),product.getProd_price()) ; 
            return ResponseEntity.status(HttpStatus.OK).body(dto) ; 
        }
        throw new ErrorClass(RuntimeErrors.NOT_FOUND,HttpStatus.NOT_FOUND) ; 
    }
}
