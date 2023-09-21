package com.example.faemarket.api.service;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.repository.ApartmentRepository;
import com.example.faemarket.service.impl.ApartmentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApartmentServiceTest {

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentServiceImpl apartmentService;
//    @BeforeEach
//    public void init(){
//
//    }
    @Test
    public void ApartmentService_SaveDuplicatedApartmentList_ReturnFail(){


        ApartmentDto apartmentDto1 = ApartmentDto.builder()
                .id("A002")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(4).build();

        List<ApartmentDto> list = new ArrayList<>();

        list.add(apartmentDto1);
        //Apartment apartment1 = Mockito.mock(Apartment.class);

        Mockito.when(apartmentRepository.existsById(apartmentDto1.getId())).thenReturn(true);


        //Mockito.verify(apartmentRepository, Mockito.times(0)).save(Mockito.any(Apartment.class));
        int fail = apartmentService.saveAllApartments(list);


        Assertions.assertThat(fail).isEqualTo(1);

    }
    @Test
    public void ApartmentService_SaveNonEmptyApartmentList_ReturnFail(){
        ApartmentDto apartmentDto =
                ApartmentDto.builder()
                        .id("A001")
                        .address("Ho Chi Minh")
                        .retailPrice("3000")
                        .numberOfRoom(3).build();

        ApartmentDto apartmentDto1 = ApartmentDto.builder()
                .id("A002")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(4).build();

        List<ApartmentDto> list = new ArrayList<>();
        list.add(apartmentDto);
        list.add(apartmentDto1);
        //Apartment apartment1 = Mockito.mock(Apartment.class);

        Mockito.when(apartmentRepository.existsById(apartmentDto1.getId())).thenReturn(false);
        Mockito.when(apartmentRepository.existsById(apartmentDto.getId())).thenReturn(false);


        //Mockito.verify(apartmentRepository, Mockito.times(0)).save(Mockito.any(Apartment.class));
        int fail = apartmentService.saveAllApartments(list);


        Assertions.assertThat(fail).isEqualTo(0);

    }
    @Test
    public void ApartmentService_SaveEmptyList_Return0(){
        List<ApartmentDto> list = new ArrayList<>();



        int fail = apartmentService.saveAllApartments(list);


        Assertions.assertThat(fail).isEqualTo(0);

    }
    @Test
    public void ApartmentService_GetAllApartmentList_ReturnList(){
        ApartmentDto apartmentDto =
                ApartmentDto.builder()
                        .id("A001")
                        .address("Ho Chi Minh")
                        .retailPrice("3000")
                        .numberOfRoom(3).build();
        Apartment apartment = ApartmentMapper.toApartment(apartmentDto);
        ApartmentDto apartmentDto1 = ApartmentDto.builder()
                .id("A002")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(4).build();
        Apartment apartment1 = ApartmentMapper.toApartment(apartmentDto1);
        List<ApartmentDto> list = new ArrayList<>();
        list.add(apartmentDto);
        list.add(apartmentDto1);
        List<Apartment> list2 = new ArrayList<>();
        list2.add(apartment);
        list2.add(apartment1);

        Mockito.when(apartmentRepository.findAll()).thenReturn(list2);

        //Mockito.verify(apartmentRepository, Mockito.times(0)).save(Mockito.any(Apartment.class));
        List<ApartmentDto> returnList = apartmentService.findAllApartments();


        Assertions.assertThat(returnList).isEqualTo(list);

    }


}
