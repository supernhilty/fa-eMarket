package com.example.faemarket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
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
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String retailPrice;
    @Column(nullable = false)
    private int numberOfRoom;

    @OneToMany(mappedBy = "apartment")
    private List<Contract> contracts;

}
