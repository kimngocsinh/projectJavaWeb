package com.springboot.WebAPI.service.impl;

import com.springboot.WebAPI.model.Supplier;
import com.springboot.WebAPI.repository.SupplierRepository;
import com.springboot.WebAPI.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository customerRepository;

    @Override
    public List<Supplier> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Supplier addCustomer(Supplier customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Supplier updateCustomer(Supplier customer, Long id) {
        Supplier updateCustomer = customerRepository.findById(id).map(newCustomer ->{

            newCustomer.setName(customer.getName());
            newCustomer.setAddress(customer.getAddress());
            return customerRepository.save(newCustomer);
        }).orElseGet(() ->{
            return customerRepository.save(customer);
        });
        return customerRepository.save(updateCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (exists){
            customerRepository.deleteById(id);
        }
    }
}
