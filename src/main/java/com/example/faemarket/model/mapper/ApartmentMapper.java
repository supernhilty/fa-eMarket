package com.example.faemarket.model.mapper;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;

public class ApartmentMapper {
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
}
