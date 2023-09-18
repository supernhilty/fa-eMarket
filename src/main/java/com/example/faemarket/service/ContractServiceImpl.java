package com.example.faemarket.service;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.mapper.ContractMapper;
import com.example.faemarket.repository.ContractRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ContractServiceImpl implements ContractService{
    @Autowired
    private ContractRepository contractRepository;
    @Override
    public Contract saveContract(ContractDto contractDto) {
        return contractRepository.save(ContractMapper.toContract(contractDto));
    }

    @Override
    public List<ContractDto> findAllContracts() {
        return contractRepository.findAll().stream().map(ContractMapper::toContractDto).collect(Collectors.toList());
    }

    @Override
    public List<Contract> saveAllContracts(List<ContractDto> contractDtos) {
        return contractRepository.saveAll(contractDtos.stream().map(ContractMapper::toContract).collect(Collectors.toList()));
    }
}
