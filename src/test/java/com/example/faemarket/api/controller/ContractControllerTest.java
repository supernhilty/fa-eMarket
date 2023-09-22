package com.example.faemarket.api.controller;

import com.example.faemarket.controller.ContractController;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.mapper.ContractMapper;
import com.example.faemarket.service.ContractService;
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

@WebMvcTest(controllers = ContractController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ContractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;
    @MockBean
    private ContractMapper contractMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void ContractController_LoadExistedFile_ReturnList() throws Exception {
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
        Mockito.when(contractService.
                findAllContracts()).thenReturn(list);
        String url = "/contract";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

    }
    @Test
    public void ContractController_LoadEmptyExistedFile_ReturnList() throws Exception {


        List<ContractDto> list = new ArrayList<>();

        Mockito.when(contractService.
                findAllContracts()).thenReturn(list);
        String url = "/contract";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());

    }

    @Test
    public void ContractController_UploadNonCSVFile_ReturnBAD_REQUEST() throws Exception {


        List<ContractDto> list = new ArrayList<>();

        Mockito.when(contractService.
                findAllContracts()).thenReturn(list);
        String url = "/contract";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());

    }
    @Test
    public void ContractController_UploadExistedFile() throws Exception {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MockMultipartFile file = new MockMultipartFile("file", "contracts.csv",
                "text/csv",
                "content".getBytes());

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

        List list = new ArrayList<>();
        list.add(contractDto);
        list.add(contractDto1);

        Mockito.when(contractMapper.
                csvToObject(file.getInputStream())).thenReturn(list);
        Mockito.when(contractService.
                saveAllContracts(any())).thenReturn(0);



        String url = "/contract/upload";


        mockMvc.perform(multipart(url)
                        .file(file))
                .andExpect(status().isOk());


    }

    @Test
    public void ContractController_UploadMissingValueFile() throws Exception {

        List<ContractDto> list = new ArrayList<>();
        ContractDto contractDto = new ContractDto();
        list.add(contractDto);
        MockMultipartFile file = new MockMultipartFile("file", "contracts.csv",
                "text/csv",
                "id,address,retailPrice,numberOfRoom\n".getBytes());

        Mockito.when(contractService.
                saveAllContracts(any())).thenReturn(0);
        String url = "/contract/upload";
        mockMvc.perform(multipart(url)
                        .file(file))
                .andExpect(status().isOk()).andExpect(content()
                        .string("Uploaded the file successfully: contracts.csv\n" +
                                "Upload successfully: 0 row(s) \n" +
                                "Fail to upload: 0 row(s)"));

    }



}

