package com.restAPI.task_rest_api.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalBookService {

    private final RestTemplate restTemplate;

    public ExternalBookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getExternalBooks() {
        // Mock URL, can be replaced with a real API
        String url = "http://localhost:8080/books/api/v1";
        return restTemplate.getForObject(url, String.class);
    }
}

