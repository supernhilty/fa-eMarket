package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.entity.Customer;
import com.example.faemarket.helper.CSVHelper;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.service.ApartmentService;
import com.example.faemarket.service.ContractService;
import com.example.faemarket.service.CustomerService;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class ContractMapper extends CSVHelper {
    @Autowired
    CustomerService  customerService;
    @Autowired
    ApartmentService apartmentService;

    public static ContractDto toContractDto(Contract contract){
        return ContractDto.builder()
                .id(contract.getId())
                .apartmentId(contract.getApartment().getId())
                .customerId(contract.getCustomer().getId())
                .startDate(contract.getStartDate().toString())
                .endDate(contract.getEndDate().toString()).build();
    }

    public static Contract toContract(ContractDto contractDto) throws ParseException {
        return Contract.builder()
                .id(contractDto.getId())
                .startDate(new SimpleDateFormat("dd/MM/yyyy").parse(contractDto.getStartDate()))
                .endDate(new SimpleDateFormat("dd/MM/yyyy").parse(contractDto.getEndDate())).build();
    }
    @Override
    public ContractDto parseToObject(CSVRecord csvRecord){
            ContractDto contractDto = new ContractDto(csvRecord.get("Id"),
                    csvRecord.get("Customer Id"),
                    csvRecord.get("Apartment Id"),
                    csvRecord.get("Start Date"),
                    csvRecord.get("End Date"));
        return contractDto;
    }
}
