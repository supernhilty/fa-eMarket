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
    public int saveApartment(ApartmentDto apartmentDto) {

            apartmentRepository.save(ApartmentMapper.toApartment(apartmentDto));

        return 0;
    }

    @Override
    public List<ApartmentDto> findAllApartments() {
        return apartmentRepository.findAll().stream().map(ApartmentMapper::toApartmentDto).collect(Collectors.toList());
    }

    @Override
    public int saveAllApartments(List<ApartmentDto> apartmentDtos) {
        int fail=0;
        for (ApartmentDto o:apartmentDtos) {
            if(apartmentRepository.existsById(o.getId())){
                fail++;
            }else{
                saveApartment(o);
            }
        }
        return fail;
    }
}
