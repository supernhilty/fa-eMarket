package com.example.faemarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String status;

    @OneToMany(mappedBy = "customer")
    private List<Contract> contracts;

}
