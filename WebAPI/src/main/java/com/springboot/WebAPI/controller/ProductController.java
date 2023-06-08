package com.springboot.WebAPI.controller;

import com.springboot.WebAPI.model.Product;
import com.springboot.WebAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/list")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestBody Product product){
        Pageable pageable = PageRequest.of(product.getPage(), product.getSize());
        Page<Product> productPage = productService.getAllProducts(product, product.getStartDate(), product.getEndDate() , pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        Product update = productService.updateProduct(product, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
