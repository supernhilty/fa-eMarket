package com.example.faemarket.service;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.model.dto.ApartmentDto;
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
    public int saveContract(ContractDto contractDto) {
        contractRepository.save(ContractMapper.toContract(contractDto));
        return 0;
    }

    @Override
    public List<ContractDto> findAllContracts() {
        return contractRepository.findAll().stream().map(ContractMapper::toContractDto).collect(Collectors.toList());
    }

    @Override
    public int saveAllContracts(List<ContractDto> contractDtos) {
        int fail=0;
        for (ContractDto o:contractDtos) {
            if(contractRepository.existsById(o.getId())){
                fail++;
            }else{
                saveContract(o);
            }
        }
        return fail;
    }
}
