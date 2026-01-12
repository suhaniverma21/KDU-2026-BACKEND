package com.assessment.quickShip.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Package {
    private String id;
    @NotBlank
    @Size(min = 1, max = 10, message = "Destination must be between 1 and 100 characters")
    private String destination;
    private Double weight;
    private String status;//Values: "PENDING" or “SORTED”
    private String deliveryType;//Values: STANDARD" or "EXPRESS"
}