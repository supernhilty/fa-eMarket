package com.example.faemarket.api.controller;

import com.example.faemarket.controller.ApartmentController;
import com.example.faemarket.entity.Apartment;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.service.ApartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


import static org.mockito.BDDMockito.given;

//@WebMvcTest(controllers = ApartmentController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
public class ApartmentControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ApartmentService apartmentService;

//    @Autowired
//    private ObjectMapper objectMapper;
//    private Apartment apartment;
//    private ApartmentDto apartmentDto;
//
//    @BeforeEach
//    public void init(){
//        apartment = Apartment.builder()
//                .id("A001")
//                .address("Ho Chi Minh")
//                .retailPrice("3000")
//                .numberOfRoom(3).build();
//        apartmentDto = ApartmentDto.builder()
//                .id("A001")
//                .address("Ho Chi Minh")
//                .retailPrice("3000")
//                .numberOfRoom(3).build();
//    }
//
////    @Test
////    public void ApartmentController_UploadFile_ReturnString() throws Exception{
//
//        @Test
//        public void testUploadFile_WithCSVFile() throws Exception {
//            // Create a mock file
//            MockMultipartFile file = new MockMultipartFile("file", "apartments.csv",
//                    "text/csv",
//                    "id,address,retailPrice,numberOfRoom\nA001,Ho Chi Minh,3000,3\nA002,Ha Noi,4000,4"
//                            .getBytes());
//
//            // Set up MockMvc
//            given(apartmentService.saveAllApartments(ArgumentMatchers.anyList()))
//                    .willReturn(0);
//
//            // Perform the POST request
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/upload")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(""))
//                    .andReturn();
//
//
//            // Verify the response
//            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//            Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo("Uploaded the file successfully: apartments.csv\nUpload successfully: 2 row(s) \nFail to upload: 0 row(s)");
//        }




}
