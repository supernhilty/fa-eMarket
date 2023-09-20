package com.example.faemarket.api.repository;



import com.example.faemarket.entity.Apartment;
import com.example.faemarket.repository.ApartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ApartmentRepositoryTest {
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Test
    public void ApartmentRepository_Save_ReturnApartment(){
        //Arrange
        Apartment apartment = Apartment.builder()
                .id("A001")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build();

        //Act
        Apartment savedApartment = apartmentRepository.save(apartment);
        //Assert
        Assertions.assertThat(savedApartment).isNotNull();

    }

    @Test
    public void ApartmentRepository_GetAll_ReturnAll() {
        Apartment apartment = Apartment.builder()
                .id("A001")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build();
        Apartment apartment2 = Apartment.builder()
                .id("A002")
                .address("Ho Chi Minh")
                .retailPrice("5000")
                .numberOfRoom(7).build();
        apartmentRepository.save(apartment);
        apartmentRepository.save(apartment2);

        List<Apartment> list = apartmentRepository.findAll();
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void ApartmentRepository_FindById_ReturnApartment(){
        //Arrange
        Apartment apartment = Apartment.builder()
                .id("A001")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build();

        //Act
        apartmentRepository.save(apartment);
        Apartment savedApartment = apartmentRepository
                .findById(apartment.getId()).get();
        //Assert
        Assertions.assertThat(savedApartment).isNotNull();

    }
}
