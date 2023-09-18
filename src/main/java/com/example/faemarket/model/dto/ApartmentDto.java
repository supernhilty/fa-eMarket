package com.example.faemarket.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApartmentDto{
    private String id;
    private String address;
    private String retailPrice;
    private int numberOfRoom;
}
