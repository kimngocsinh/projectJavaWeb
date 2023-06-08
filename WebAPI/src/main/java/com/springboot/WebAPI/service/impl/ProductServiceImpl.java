package com.springboot.WebAPI.service.impl;

import com.springboot.WebAPI.model.Product;
import com.springboot.WebAPI.repository.ProductRepository;
import com.springboot.WebAPI.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }



    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product updateProduct =productRepository.findById(id).map(newProduct ->{
            newProduct.setName(product.getName());
            newProduct.setDescription(product.getDescription());
            newProduct.setCostPrice(product.getCostPrice());
            newProduct.setCurrentQuantity(product.getCurrentQuantity());
            newProduct.setCategory(product.getCategory());
            newProduct.setSupplier(product.getSupplier());
            return productRepository.save(newProduct);

        }).orElseGet(() -> {
            return productRepository.save(product);
        });
        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if(exists){
            productRepository.deleteById(id);
        }
    }

    @Override
    public Page<Product> getAllProducts(Product product, Date startDate, Date endDate, Pageable pageable) {
        Page<Product> page = this.productRepository.findAll(new Specification<Product>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (product.getName() != null) {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%" + product.getName() + "%")
                    ));
                }

                if (startDate != null && endDate != null) {
                    predicates.add(criteriaBuilder.between(root.get("createdDate"), startDate, endDate));
                }

                // TODO Auto-generated method stub
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        return page;
    }
}
