package com.hands_on.JDBC.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftType {
    private Long id;
    private String name;
    private String description;
    private Boolean activeStatus;
    private Long tenantId;
}

