package com.restAPI.task_rest_api.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private Long id;
    private String title;
    private String author;
}
