package com.springboot.WebAPI.controller;

import com.springboot.WebAPI.model.Supplier;
import com.springboot.WebAPI.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierController {
    @Autowired
    private SupplierService customerService;
    @GetMapping("/list")
    public ResponseEntity<List<Supplier>> getAllCustomers(){
        List<Supplier> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
    @PostMapping("/add-supplier")
    public ResponseEntity<Supplier> addCustomer(@RequestBody Supplier customer){
        Supplier addCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(addCustomer, HttpStatus.OK);
    }
    @PutMapping("/update-supplier/{id}")
    public ResponseEntity<Supplier> updateCustomer(@RequestBody Supplier customer, @PathVariable("id") Long id){
        Supplier updateCustomer = customerService.updateCustomer(customer, id);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }
    @DeleteMapping("/delete-supplier/{id}")
    public ResponseEntity<Supplier> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
