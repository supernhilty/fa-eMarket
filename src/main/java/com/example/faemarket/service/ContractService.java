package com.example.faemarket.service;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.model.dto.ContractDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContractService {

    Contract saveContract(ContractDto contractDto);
    List<ContractDto> findAllContracts();
    List<Contract> saveAllContracts(List<ContractDto> contractDtos);
}
