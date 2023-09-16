package com.example.faemarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String status;
}
