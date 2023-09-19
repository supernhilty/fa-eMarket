package com.example.faemarket.model.dto;

import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContractDto {
    private String id;
    private String customerId;
    private String apartmentId;
    private String startDate;
    private String endDate;
}
