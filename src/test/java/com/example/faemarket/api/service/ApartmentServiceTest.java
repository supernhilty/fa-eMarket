package com.example.faemarket.api.service;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.repository.ApartmentRepository;
import com.example.faemarket.service.impl.ApartmentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApartmentServiceTest {

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    @Test
    public void ApartmentService_SaveDuplicatedApartment_ReturnFalse(){
        ApartmentDto apartment =
                ApartmentDto.builder()
                .id("A002")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build();
        ApartmentDto apartment2 = ApartmentDto.builder()
                .id("A002")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(4).build();
        //Mockito.when(apartmentRepository.save(Mockito.any(Apartment.class))).thenReturn(Mockito.any(Apartment.class));
        //Mockito.verify(apartmentRepository, Mockito.times(0)).save(Mockito.any(Apartment.class));
        boolean test = apartmentService.saveApartment(apartment);
        test = apartmentService.saveApartment(apartment2);

        Assertions.assertThat(test).isEqualTo(false);

    }
    @Test
    public void ApartmentService_SaveEmpty_ReturnFalse(){
        ApartmentDto apartment = new ApartmentDto();

        Mockito.verify(apartmentRepository, Mockito.times(0)).save(Mockito.any(Apartment.class));
        boolean test = apartmentService.saveApartment(apartment);

        Assertions.assertThat(test).isEqualTo(false);

    }

}
