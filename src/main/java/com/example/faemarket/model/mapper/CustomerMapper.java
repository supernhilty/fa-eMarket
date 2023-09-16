package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.CustomerDto;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(customer.getAddress())
                .age(customer.getAge())
                .status(customer.getStatus())
                .build();
    }

    public static Customer toCustomer(CustomerDto customerDto){
        return  Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .address(customerDto.getAddress())
                .age(customerDto.getAge())
                .status(customerDto.getStatus())
                .build();
    }
}
