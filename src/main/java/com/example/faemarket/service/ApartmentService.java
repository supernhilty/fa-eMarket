package com.example.faemarket.service;


import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;


import java.util.List;
import java.util.Optional;


public interface ApartmentService {
    boolean saveApartment(ApartmentDto apartmentDto);
    List<ApartmentDto> findAllApartments();
    int saveAllApartments(List<ApartmentDto> apartmentDtos);
    public Optional<Apartment> findApartmentById(String id);
}
