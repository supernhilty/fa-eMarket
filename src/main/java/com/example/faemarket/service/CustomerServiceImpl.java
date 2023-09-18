package com.example.faemarket.service;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.CustomerDto;
import com.example.faemarket.model.mapper.CustomerMapper;
import com.example.faemarket.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
         return customerRepository.save(CustomerMapper.toCustomer(customerDto));
    }

    @Override
    public List<Customer> saveAllCustomers(List<CustomerDto> customerDtos) {
        return customerRepository.saveAll(customerDtos.stream().map(CustomerMapper::toCustomer).collect(Collectors.toList()));
    }
    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDto).collect(Collectors.toList());
    }

}
