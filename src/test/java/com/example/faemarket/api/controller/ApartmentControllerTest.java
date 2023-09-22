package com.example.faemarket.api.controller;

import com.example.faemarket.controller.ApartmentController;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.service.ApartmentService;
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
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ApartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ApartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmentService apartmentService;
    @MockBean
    private ApartmentMapper apartmentMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void ApartmentController_LoadExistedFile_ReturnList() throws Exception {
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
        Mockito.when(apartmentService.
                findAllApartments()).thenReturn(list);
        String url = "/apartment";
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

    }
    @Test
    public void ApartmentController_LoadEmptyExistedFile_ReturnList() throws Exception {


        List<ApartmentDto> list = new ArrayList<>();

        Mockito.when(apartmentService.
                findAllApartments()).thenReturn(list);
        String url = "/apartment";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());

    }


    @Test
    public void ApartmentController_UploadNonCSVFile_ReturnBAD_REQUEST() throws Exception {


        List<ApartmentDto> list = new ArrayList<>();

        Mockito.when(apartmentService.
                findAllApartments()).thenReturn(list);
        String url = "/apartment";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());

    }
    @Test
    public void ApartmentController_UploadExistedFile() throws Exception {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MockMultipartFile file = new MockMultipartFile("file", "apartments.csv",
                "text/csv",
                "id,address,retailPrice,numberOfRoom\nA001,Ho Chi Minh,3000,3\nA002,Ha Noi,4000,4".getBytes());

        ApartmentDto apartmentDto =
                ApartmentDto.builder()
                        .id("A001")
                        .address("Ho Chi Minh")
                        .retailPrice("3000")
                        .numberOfRoom(3).build();

        ApartmentDto apartmentDto1 = ApartmentDto.builder()
                .id("A002")
                .address("Ha Noi")
                .retailPrice("4000")
                .numberOfRoom(4).build();

        List list = new ArrayList<>();
        list.add(apartmentDto);
        list.add(apartmentDto1);

        Mockito.when(apartmentMapper.
                csvToObject(file.getInputStream())).thenReturn(list);
        Mockito.when(apartmentService.
                saveAllApartments(any())).thenReturn(0);



        String url = "/apartment/upload";


        mockMvc.perform(multipart(url)
                        .file(file))
                .andExpect(status().isOk());
//                .andExpect(content().string("Uploaded the file successfully: apartments.csv\n" +
//                        "Upload successfully: 2 row(s) \n" +
//                        "Fail to upload: 0 row(s)"));

     //   verify(apartmentService, times(2)).saveApartment(any());
    }

    @Test
    public void ApartmentController_UploadMissingValueFile() throws Exception {
        List<ApartmentDto> list = new ArrayList<>();
        ApartmentDto apartmentDto = new ApartmentDto();
        list.add(apartmentDto);
        MockMultipartFile file = new MockMultipartFile("file", "apartments.csv",
                "text/csv",
                "id,address,retailPrice,numberOfRoom\n".getBytes());

        Mockito.when(apartmentService.
                saveAllApartments(any())).thenReturn(0);
        String url = "/apartment/upload";
        mockMvc.perform(multipart(url)
                        .file(file))
                .andExpect(status().isOk()).andExpect(content()
                        .string("Uploaded the file successfully: apartments.csv\n" +
                "Upload successfully: 0 row(s) \n" +
                "Fail to upload: 0 row(s)"));

    }



}
