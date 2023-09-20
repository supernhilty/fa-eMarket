package com.example.faemarket.service.impl;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.repository.ApartmentRepository;
import com.example.faemarket.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public boolean saveApartment(ApartmentDto apartmentDto) {
        try {

            if(apartmentRepository.existsById(apartmentDto.getId())){
                Apartment apartment = apartmentRepository.save(ApartmentMapper.toApartment(apartmentDto));
                return true;
            }

        }catch (Exception e){
            return false;
        }
        return false;
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
                try{
                    if(!saveApartment(o)){
                        fail++;
                    }
                }catch(Exception e){
                    fail++;
                }

            }
        }
        return fail;
    }

    public Optional<Apartment> findApartmentById(String id){
        return apartmentRepository.findById(id);
    }
}
