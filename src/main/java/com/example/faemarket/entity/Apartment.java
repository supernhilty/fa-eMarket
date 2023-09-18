package com.example.faemarket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="apartment")
public class Apartment {
    @Id
    private String id;
    private String address;
    private String retailPrice;
    private int numberOfRoom;

    @OneToMany(mappedBy = "apartment")
    private List<Contract> contracts;

}
