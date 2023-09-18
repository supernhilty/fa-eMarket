package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.helper.CSVHelper;
import com.example.faemarket.model.dto.ApartmentDto;
import org.apache.commons.csv.CSVRecord;

public class ApartmentMapper extends CSVHelper {
    public static ApartmentDto toApartmentDto(Apartment apartment){
        return ApartmentDto.builder()
                .id(apartment.getId())
                .address(apartment.getAddress())
                .retailPrice(apartment.getRetailPrice())
                .numberOfRoom(apartment.getNumberOfRoom()).build();
    }

    public static Apartment toApartment(ApartmentDto apartmentDto){
        return Apartment.builder()
                .id(apartmentDto.getId())
                .address(apartmentDto.getAddress())
                .retailPrice(apartmentDto.getRetailPrice())
                .numberOfRoom(apartmentDto.getNumberOfRoom()).build();
    }
    @Override
    public ApartmentDto parseToObject(CSVRecord csvRecord){
        ApartmentDto apartmentDto = new ApartmentDto(
                        csvRecord.get("Id"),
                        csvRecord.get("Address"),
                        csvRecord.get("Retail Price"),
                        Integer.parseInt(csvRecord.get("Number of room")));
        return apartmentDto;
    };
}
