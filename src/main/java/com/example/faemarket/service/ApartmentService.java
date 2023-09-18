package com.example.faemarket.service;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApartmentService {
    Apartment saveApartment(ApartmentDto apartmentDto);
    List<ApartmentDto> findAllApartments();
    List<Apartment> saveAllApartments(List<ApartmentDto> apartmentDtos);
}
