package com.example.faemarket.service;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ApartmentServiceImpl implements ApartmentService{
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public Apartment saveApartment(ApartmentDto apartmentDto) {
        return apartmentRepository.save(ApartmentMapper.toApartment(apartmentDto));
    }

    @Override
    public List<ApartmentDto> findAllApartments() {
        return apartmentRepository.findAll().stream().map(ApartmentMapper::toApartmentDto).collect(Collectors.toList());
    }

    @Override
    public List<Apartment> saveAllApartments(List<ApartmentDto> apartmentDtos) {
        return apartmentRepository.saveAll(apartmentDtos.stream().map(ApartmentMapper::toApartment).collect(Collectors.toList()));
    }
}
