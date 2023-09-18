package com.example.faemarket.service;


import com.example.faemarket.model.dto.ApartmentDto;


import java.util.List;


public interface ApartmentService {
    int saveApartment(ApartmentDto apartmentDto);
    List<ApartmentDto> findAllApartments();
    int saveAllApartments(List<ApartmentDto> apartmentDtos);
}
