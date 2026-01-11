package com.springSecurity.hands_on.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Book {
    private Integer id;
    private String title;
    private String status;//Values: "PROCESSING" or "AVAILABLE"
    private String author;
}
