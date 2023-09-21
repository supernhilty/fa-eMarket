package com.example.faemarket.api.service;

import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.CustomerDto;
import com.example.faemarket.model.mapper.CustomerMapper;
import com.example.faemarket.repository.CustomerRepository;
import com.example.faemarket.service.impl.CustomerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;
    //    @BeforeEach
//    public void init(){
//
//    }
    @Test
    public void CustomerService_SaveDuplicatedCustomerList_ReturnFail(){
        CustomerDto customerDto =
                CustomerDto.builder()
                        .id("C001")
                        .firstName("Nhi")
                        .lastName("Le Thi Yen")
                        .age(20)
                        .address("Quang Tri")
                        .status("active").build();



        List<CustomerDto> list = new ArrayList<>();
        list.add(customerDto);

        //Customer customer1 = Mockito.mock(Customer.class);

        Mockito.when(customerRepository.existsById(customerDto.getId())).thenReturn(true);


        //Mockito.verify(customerRepository, Mockito.times(0)).save(Mockito.any(Customer.class));
        int fail = customerService.saveAllCustomers(list);


        Assertions.assertThat(fail).isEqualTo(1);

    }
    @Test
    public void CustomerService_SaveNonEmptyCustomerList_ReturnFail(){
        CustomerDto customerDto =
                CustomerDto.builder()
                        .id("C001")
                        .firstName("Nhi")
                        .lastName("Le Thi Yen")
                        .age(20)
                        .address("Quang Tri")
                        .status("active").build();

        CustomerDto customerDto1 = CustomerDto.builder()
                .id("C001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();

        List<CustomerDto> list = new ArrayList<>();
        list.add(customerDto);
        list.add(customerDto1);
        //Customer customer1 = Mockito.mock(Customer.class);

        Mockito.when(customerRepository.existsById(customerDto1.getId())).thenReturn(false);
        Mockito.when(customerRepository.existsById(customerDto.getId())).thenReturn(false);


        //Mockito.verify(customerRepository, Mockito.times(0)).save(Mockito.any(Customer.class));
        int fail = customerService.saveAllCustomers(list);


        Assertions.assertThat(fail).isEqualTo(0);

    }
    @Test
    public void CustomerService_SaveEmptyList_Return0(){
        List<CustomerDto> list = new ArrayList<>();



        int fail = customerService.saveAllCustomers(list);


        Assertions.assertThat(fail).isEqualTo(0);

    }
    @Test
    public void CustomerService_GetAllCustomerList_ReturnList(){
        CustomerDto customerDto =
                CustomerDto.builder()
                        .id("C001")
                        .firstName("Nhi")
                        .lastName("Le Thi Yen")
                        .age(20)
                        .address("Quang Tri")
                        .status("active").build();
        Customer customer = CustomerMapper.toCustomer(customerDto);
        CustomerDto customerDto1 = CustomerDto.builder()
                .id("C001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();
        Customer customer1 = CustomerMapper.toCustomer(customerDto1);
        List<CustomerDto> list = new ArrayList<>();
        list.add(customerDto);
        list.add(customerDto1);
        List<Customer> list2 = new ArrayList<>();
        list2.add(customer);
        list2.add(customer1);

        Mockito.when(customerRepository.findAll()).thenReturn(list2);

        //Mockito.verify(customerRepository, Mockito.times(0)).save(Mockito.any(Customer.class));
        List<CustomerDto> returnList = customerService.findAllCustomers();


        Assertions.assertThat(returnList).isEqualTo(list);

    }


}
