package com.example.faemarket.service.impl;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.entity.Contract;
import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.mapper.ContractMapper;
import com.example.faemarket.repository.ApartmentRepository;
import com.example.faemarket.repository.ContractRepository;
import com.example.faemarket.repository.CustomerRepository;
import com.example.faemarket.service.ContractService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Override
    public boolean saveContract(ContractDto contractDto) throws ParseException {
        try {
            Optional<Customer> cus = customerRepository.findById(contractDto.getCustomerId());
            Optional<Apartment> apartment = apartmentRepository.findById(contractDto.getApartmentId());
            Contract contract = ContractMapper.toContract(contractDto);
            if(cus.isEmpty()||apartment.isEmpty()){
                return false;
            }else{
                contract.setCustomer(cus.get());
                contract.setApartment(apartment.get());
                contractRepository.save(contract);
            }
        }catch(Exception e){
            return false;
        }


        return true;
    }

    @Override
    public List<ContractDto> findAllContracts() {
        return contractRepository.findAll().stream().map(ContractMapper::toContractDto).collect(Collectors.toList());
    }

    @Override
    public int saveAllContracts(List<ContractDto> contractDtos)  {
        int fail=0;
        for (ContractDto o:contractDtos) {
            if(contractRepository.existsById(o.getId())){
                fail++;
            }else{
                try{
                    if(!saveContract(o)){
                    fail++;
                }

                }catch (Exception e){
                    fail++;
                }

            }
        }
        return fail;
    }

}
