package com.example.faemarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContractDto {
    private String id;
    private String customerId;
    private String apartmentId;
    private Date startDate;
    private Date endDate;
}
