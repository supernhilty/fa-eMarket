package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.helper.CSVHelper;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.dto.CustomerDto;
import org.apache.commons.csv.CSVRecord;

import java.sql.Date;

public class CustomerMapper extends CSVHelper {
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
    @Override
    public CustomerDto parseToObject(CSVRecord csvRecord){
        CustomerDto customerDto = new CustomerDto(csvRecord.get("Id"),
                csvRecord.get("First Name"),
                csvRecord.get("Last Name"),
                csvRecord.get("Address"),
                Integer.parseInt(csvRecord.get("Age")),
               csvRecord.get("Status"));
        return customerDto;
    }
}
