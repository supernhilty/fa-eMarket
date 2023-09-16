package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.model.dto.ContractDto;

public class ContractMapper {
    public static ContractDto toContractDto(Contract contract){
        return ContractDto.builder()
                .id(contract.getId())
                .customerId(contract.getCustomerId())
                .apartmentId(contract.getApartmentId())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate()).build();
    }

    public static Contract toContract(ContractDto contractDto){
        return Contract.builder()
                .id(contractDto.getId())
                .customerId(contractDto.getCustomerId())
                .apartmentId(contractDto.getApartmentId())
                .startDate(contractDto.getStartDate())
                .endDate(contractDto.getEndDate()).build();
    }
}
