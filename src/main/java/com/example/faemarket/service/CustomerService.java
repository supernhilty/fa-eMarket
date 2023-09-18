package com.example.faemarket.service;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    int saveCustomer(CustomerDto customerDto);
    int saveAllCustomers(List<CustomerDto> customerDtos);
    List<CustomerDto> findAllCustomers();
}
