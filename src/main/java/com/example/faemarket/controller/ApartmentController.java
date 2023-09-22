package com.example.faemarket.controller;

import com.example.faemarket.helper.CSVHelper;
import com.example.faemarket.model.dto.ApartmentDto;
import com.example.faemarket.model.mapper.ApartmentMapper;
import com.example.faemarket.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private ApartmentMapper apartmentMapper;

    public ApartmentController(ApartmentService apartmentServiceMock, ApartmentMapper apartmentMapperMock) {
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        int fail = 0;
        int succeed = 0;
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                List<?> list =  apartmentMapper.csvToObject(file.getInputStream());
                List<ApartmentDto> list1 = (List<ApartmentDto>) list;

                fail = apartmentService.saveAllApartments(list1);
                succeed = list.size()-fail;
                message = "Uploaded the file successfully: " + file.getOriginalFilename()
                        + "\nUpload successfully: "+succeed+" row(s) \nFail to upload: " + fail+ " row(s)";

                return ResponseEntity.status(HttpStatus.OK).body(new String(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new String(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(message));
    }

    @GetMapping("")
    public ResponseEntity<List<ApartmentDto>> getAllApartment() {
        try {
            List<ApartmentDto> apartments = apartmentService.findAllApartments();

            if (apartments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(apartments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

