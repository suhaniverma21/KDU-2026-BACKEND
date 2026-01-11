package com.springSecurity.hands_on.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequestDTO {
    private Integer id;
    private String title;
    private String status; //Values: "PROCESSING" or "AVAILABLE"
}
