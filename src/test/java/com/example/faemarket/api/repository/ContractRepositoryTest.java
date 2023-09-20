package com.example.faemarket.api.repository;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.entity.Contract;
import com.example.faemarket.entity.Customer;
import com.example.faemarket.repository.ContractRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContractRepositoryTest {
    @Autowired
    private ContractRepository contractRepository;
    @Test
    public void ContractRepository_Save_ReturnContract(){
        //Arrange
        Contract contract = null;
        try {
            contract = Contract.builder()
                    .id("A001")
                    .customer(Customer.builder()
                            .id("C001")
                            .firstName("Nhi")
                            .lastName("Le Thi Yen")
                            .age(20)
                            .address("Quang Tri")
                            .status("active").build())
                    .apartment(Apartment.builder()
                            .id("A001")
                            .address("Ho Chi Minh")
                            .retailPrice("3000")
                            .numberOfRoom(3).build())
                    .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023"))
                    .endDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023")).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //Act
        Contract savedContract = contractRepository.save(contract);
        //Assert
        Assertions.assertThat(savedContract).isNotNull();

    }

    @Test
    public void ContractRepository_GetAll_ReturnAll() {
        Contract contract = null;
        try {
            contract = Contract.builder()
                    .id("A001")
                    .customer(Customer.builder()
                            .id("C001")
                            .firstName("Nhi")
                            .lastName("Le Thi Yen")
                            .age(20)
                            .address("Quang Tri")
                            .status("active").build())
                    .apartment(Apartment.builder()
                            .id("A001")
                            .address("Ho Chi Minh")
                            .retailPrice("3000")
                            .numberOfRoom(3).build())
                    .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023"))
                    .endDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023")).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Contract contract2 = null;
        try {
            contract2 = Contract.builder()
                    .id("A002")
                    .customer(Customer.builder()
                            .id("C001")
                            .firstName("Nhi")
                            .lastName("Le Thi Yen")
                            .age(20)
                            .address("Quang Tri")
                            .status("active").build())
                    .apartment(Apartment.builder()
                            .id("A001")
                            .address("Ho Chi Minh")
                            .retailPrice("3000")
                            .numberOfRoom(3).build())
                    .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023"))
                    .endDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023")).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        contractRepository.save(contract);
        contractRepository.save(contract2);

        List<Contract> list = contractRepository.findAll();
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void ContractRepository_FindById_ReturnContract(){
        //Arrange
        Contract contract = null;
        try {
            contract = Contract.builder()
                    .id("A001")
                    .customer(Customer.builder()
                            .id("C001")
                            .firstName("Nhi")
                            .lastName("Le Thi Yen")
                            .age(20)
                            .address("Quang Tri")
                            .status("active").build())
                    .apartment(Apartment.builder()
                            .id("A001")
                            .address("Ho Chi Minh")
                            .retailPrice("3000")
                            .numberOfRoom(3).build())
                    .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023"))
                    .endDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/9/2023")).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //Act
        contractRepository.save(contract);
        Contract savedContract = contractRepository
                .findById(contract.getId()).get();
        //Assert
        Assertions.assertThat(savedContract).isNotNull();

    }
}
