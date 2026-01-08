package com.restAPI.task_rest_api.Controller;

import com.restAPI.task_rest_api.Service.ExternalBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
public class ExternalController {

    private final ExternalBookService service;

    public ExternalController(ExternalBookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public String getBooks() {
        return service.getExternalBooks();
    }
}

