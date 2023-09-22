package com.example.faemarket.api.controller;

import com.example.faemarket.controller.CustomerController;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.dto.CustomerDto;
import com.example.faemarket.model.mapper.CustomerMapper;
import com.example.faemarket.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    @MockBean
    private CustomerMapper customerMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void CustomerController_LoadExistedFile_ReturnList() throws Exception {
        CustomerDto customerDto =
                CustomerDto.builder()
                        .id("C001")
                        .firstName("Nhi")
                        .lastName("Le Thi Yen")
                        .age(20)
                        .address("Quang Tri")
                        .status("active").build();

        CustomerDto customerDto1 = CustomerDto.builder()
                .id("C002")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();

        List<CustomerDto> list = new ArrayList<>();
        list.add(customerDto);
        list.add(customerDto1);
        Mockito.when(customerService.
                findAllCustomers()).thenReturn(list);
        String url = "/customer";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

    }
    @Test
    public void CustomerController_LoadEmptyExistedFile_ReturnList() throws Exception {


        List<CustomerDto> list = new ArrayList<>();

        Mockito.when(customerService.
                findAllCustomers()).thenReturn(list);
        String url = "/customer";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());

    }

    @Test
    public void CustomerController_UploadNonCSVFile_ReturnBAD_REQUEST() throws Exception {


        List<CustomerDto> list = new ArrayList<>();

        Mockito.when(customerService.
                findAllCustomers()).thenReturn(list);
        String url = "/customer";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());

    }
    @Test
    public void CustomerController_UploadExistedFile() throws Exception {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MockMultipartFile file = new MockMultipartFile("file", "customers.csv",
                "text/csv",
                "content".getBytes());

        CustomerDto customerDto =
                CustomerDto.builder()
                        .id("C001")
                        .firstName("Nhi")
                        .lastName("Le Thi Yen")
                        .age(20)
                        .address("Quang Tri")
                        .status("active").build();

        CustomerDto customerDto1 = CustomerDto.builder()
                .id("C002")
                .firstName("Nhi")
                .lastName("Le Thi Yen")
                .age(20)
                .address("Quang Tri")
                .status("active").build();

        List list = new ArrayList<>();
        list.add(customerDto);
        list.add(customerDto1);

        Mockito.when(customerMapper.
                csvToObject(file.getInputStream())).thenReturn(list);
        Mockito.when(customerService.
                saveAllCustomers(any())).thenReturn(0);



        String url = "/apartment/upload";


        mockMvc.perform(multipart(url)
                        .file(file))
                .andExpect(status().isOk());

    }

    @Test
    public void CustomerController_UploadMissingValueFile() throws Exception {
        List<ApartmentDto> list = new ArrayList<>();
        ApartmentDto apartmentDto = new ApartmentDto();
        list.add(apartmentDto);
        MockMultipartFile file = new MockMultipartFile("file", "customer.csv",
                "text/csv",
                "id,address,retailPrice,numberOfRoom\n".getBytes());

        Mockito.when(customerService.
                saveAllCustomers(any())).thenReturn(0);
        String url = "/apartment/upload";
        mockMvc.perform(multipart(url)
                        .file(file))
                .andExpect(status().isOk()).andExpect(content()
                        .string("Uploaded the file successfully: customer.csv\n" +
                                "Upload successfully: 0 row(s) \n" +
                                "Fail to upload: 0 row(s)"));

    }



}
