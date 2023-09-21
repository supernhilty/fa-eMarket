package com.example.faemarket.service.impl;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.dto.CustomerDto;
import com.example.faemarket.model.mapper.CustomerMapper;
import com.example.faemarket.repository.CustomerRepository;
import com.example.faemarket.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public boolean saveCustomer(CustomerDto customerDto) {
        try {
            customerRepository.save(CustomerMapper.toCustomer(customerDto));
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public int saveAllCustomers(List<CustomerDto> customerDtos) {
        int fail=0;
        for (CustomerDto o:customerDtos) {
            if(customerRepository.existsById(o.getId())||o.getId().isBlank()){
                fail++;
            }else{

                if(!saveCustomer(o)){
                    fail++;
                }

            }
        }
        return fail;
    }
    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findCustomerById(String id) {
        return customerRepository.findById(id);
    }

}
