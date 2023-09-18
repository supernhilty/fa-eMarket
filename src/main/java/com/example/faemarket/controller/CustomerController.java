package com.example.faemarket.controller;

import com.example.faemarket.helper.CSVHelper;
import com.example.faemarket.model.dto.ContractDto;
import com.example.faemarket.model.dto.CustomerDto;
import com.example.faemarket.model.mapper.ContractMapper;
import com.example.faemarket.model.mapper.CustomerMapper;
import com.example.faemarket.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        int fail = 0;
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                List<CustomerDto> list = (List<CustomerDto>) customerMapper.csvToObject(file.getInputStream());

                fail = customerService.saveAllCustomers(list);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new String(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new String(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(message));
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        try {
            List<CustomerDto> customerDtos = customerService.findAllCustomers();

            if (customerDtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
