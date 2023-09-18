package com.example.faemarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="contract")
public class Contract {
    @Id
    private String id;

    private Date startDate;
    private Date endDate;

    @ManyToOne()
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "appartmentId")
    private Apartment apartment;
}
