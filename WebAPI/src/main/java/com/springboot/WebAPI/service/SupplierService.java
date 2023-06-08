package com.springboot.WebAPI.service;

import com.springboot.WebAPI.model.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllCustomers();
    Supplier addCustomer(Supplier customer);
    Supplier updateCustomer(Supplier customer, Long id);
    void deleteCustomer(Long id);
}
