package com.example.faemarket.api.repository;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void CustomerRepository_Save_ReturnCustomer(){
        //Arrange
        Customer customer = Customer.builder()
                .id("C001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();

        //Act
        Customer savedCustomer = customerRepository.save(customer);
        //Assert
        Assertions.assertThat(savedCustomer).isNotNull();

    }

    @Test
    public void CustomerRepository_GetAll_ReturnAll() {
        Customer customer = Customer.builder()
                .id("C001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();
        Customer customer2 = Customer.builder()
                .id("C002")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Ho Chi Minh")
                .status("active").build();
        customerRepository.save(customer);
        customerRepository.save(customer2);

        List<Customer> list = customerRepository.findAll();
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void CustomerRepository_FindById_ReturnCustomer(){
        //Arrange
        Customer customer = Customer.builder()
                .id("C001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();

        //Act
         customerRepository.save(customer);
        Customer savedCustomer = customerRepository
                .findById(customer.getId()).get();
        //Assert
        Assertions.assertThat(savedCustomer).isNotNull();

    }

}
