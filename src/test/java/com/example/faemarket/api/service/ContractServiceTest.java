package com.example.faemarket.api.service;

import com.example.faemarket.entity.Apartment;
import com.example.faemarket.entity.Contract;
import com.example.faemarket.entity.Customer;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.mapper.ContractMapper;
import com.example.faemarket.repository.ApartmentRepository;
import com.example.faemarket.repository.ContractRepository;
import com.example.faemarket.repository.CustomerRepository;
import com.example.faemarket.service.ContractService;
import com.example.faemarket.service.impl.ContractServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {
    @Mock
    private ContractRepository contractRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ContractServiceImpl contractService;
    //    @BeforeEach
//    public void init(){
//
//    }
    @Test
    public void ContractService_SaveDuplicatedContractList_ReturnFail(){
        Optional<Customer> customer = Optional.of(Customer.builder()
                .id("Cus001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build());
        Optional<Apartment> apartment = Optional.of(Apartment.builder()
                .id("A001")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build());
        ContractDto contractDto =
                ContractDto.builder()
                        .id("C001")
                        .customerId("Cus001")
                        .apartmentId("A001")
                        .startDate("20/09/2023")
                        .endDate("20/09/2023").build();



        List<ContractDto> list = new ArrayList<>();
        list.add(contractDto);

        //Contract contract1 = Mockito.mock(Contract.class);

//        Mockito.when(customerRepository.findById(customer.get().getId())).thenReturn(customer);
//        Mockito.when(apartmentRepository.findById(apartment.get().getId())).thenReturn(apartment);
        Mockito.when(contractRepository.existsById(contractDto.getId())).thenReturn(true);
        int fail = contractService.saveAllContracts(list);


        Assertions.assertThat(fail).isEqualTo(1);


    }
    @Test
    public void ContractService_SaveNonEmptyContractList_ReturnFail(){
        Optional<Customer> customer = Optional.of(Customer.builder()
                .id("Cus001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build());
        Optional<Apartment> apartment = Optional.of(Apartment.builder()
                .id("A001")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build());
        ContractDto contractDto =
                ContractDto.builder()
                        .id("C001")
                        .customerId("Cus001")
                        .apartmentId("A001")
                        .startDate("20/09/2023")
                        .endDate("20/09/2023").build();

        ContractDto contractDto1 =  ContractDto.builder()
                .id("C002")
                .customerId("Cus001")
                .apartmentId("A001")
                .startDate("20/09/2023")
                .endDate("20/09/2023").build();

        List<ContractDto> list = new ArrayList<>();
        list.add(contractDto);
        list.add(contractDto1);
        //Contract contract1 = Mockito.mock(Contract.class);
        Mockito.when(contractRepository.existsById(contractDto1.getId())).thenReturn(false);
        Mockito.when(contractRepository.existsById(contractDto.getId())).thenReturn(false);
        Mockito.when(customerRepository.findById(customer.get().getId())).thenReturn(customer);
        Mockito.when(apartmentRepository.findById(apartment.get().getId())).thenReturn(apartment);
        //Mockito.verify(contractRepository, Mockito.times(0)).save(Mockito.any(Contract.class));
        int fail = contractService.saveAllContracts(list);


        Assertions.assertThat(fail).isEqualTo(0);

    }
    @Test
    public void ContractService_SaveEmptyList_Return0(){
        List<ContractDto> list = new ArrayList<>();



        int fail = contractService.saveAllContracts(list);


        Assertions.assertThat(fail).isEqualTo(0);

    }
    @Test
    public void ContractService_GetAllContractList_ReturnList() throws ParseException {
        Customer customer = Customer.builder()
                .id("C001")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();
        Apartment apartment = Apartment.builder()
                .id("A001")
                .address("Ho Chi Minh")
                .retailPrice("3000")
                .numberOfRoom(3).build();

        Contract contract = Contract.builder()
                .id("C001")
                .customer(customer)
                .apartment(apartment)
                .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/09/2023"))
                .endDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/09/2023")).build();
        Contract contract1 = Contract.builder()
                .id("C001")
                .customer(customer)
                .apartment(apartment)
                .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/09/2023"))
                .endDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/09/2023")).build();

        ContractDto contractDto =
                ContractDto.builder()
                        .id("C001")
                        .customerId("Cus001")
                        .apartmentId("A001")
                        .startDate("20/09/2023")
                        .endDate("20/09/2023").build();

        ContractDto contractDto1 =  ContractDto.builder()
                .id("C002")
                .customerId("Cus001")
                .apartmentId("A001")
                .startDate("20/09/2023")
                .endDate("20/09/2023").build();



        List<ContractDto> list = new ArrayList<>();
        list.add(contractDto);
        list.add(contractDto1);
        List<Contract> list2 = new ArrayList<>();
        list2.add(contract);
        list2.add(contract1);

        Mockito.when(contractRepository.findAll()).thenReturn(list2);

        //Mockito.verify(contractRepository, Mockito.times(0)).save(Mockito.any(Contract.class));
        List<ContractDto> returnList = contractService.findAllContracts();


        Assertions.assertThat(returnList.size()).isEqualTo(list.size());

    }

}
