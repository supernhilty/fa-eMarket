package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Contract;
import com.example.faemarket.helper.CSVHelper;
import com.example.faemarket.model.dto.ContractDto;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
public class ContractMapper extends CSVHelper {
    public static ContractDto toContractDto(Contract contract){
        return ContractDto.builder()
                .id(contract.getId())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate()).build();
    }

    public static Contract toContract(ContractDto contractDto){
        return Contract.builder()
                .id(contractDto.getId())
                .startDate(contractDto.getStartDate())
                .endDate(contractDto.getEndDate()).build();
    }
    @Override
    public ContractDto parseToObject(CSVRecord csvRecord){
            ContractDto contractDto = new ContractDto(csvRecord.get("Id"),
                    csvRecord.get("customerId"),
                    csvRecord.get("apartmentId"),
                    Date.valueOf(csvRecord.get("startDate")),
                    Date.valueOf(csvRecord.get("endDate")));
        return contractDto;
    }
}
