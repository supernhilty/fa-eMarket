package com.example.faemarket.service;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.model.dto.ContractDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


public interface ContractService {

    boolean saveContract(ContractDto contractDto) throws ParseException;
    List<ContractDto> findAllContracts();
    int saveAllContracts(List<ContractDto> contractDtos) throws ParseException;
}
