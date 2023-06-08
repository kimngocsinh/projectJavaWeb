package com.springboot.WebAPI.service;

import com.springboot.WebAPI.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    Page<Product> getAllProducts(Product product, Date startDate, Date endDate, Pageable pageable);
    /*Page<Product> getAllProducts(Product product, Pageable pageable);*/
}
