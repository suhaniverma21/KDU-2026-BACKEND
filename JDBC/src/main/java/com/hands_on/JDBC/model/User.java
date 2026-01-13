package com.hands_on.JDBC.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private Boolean loggedInStatus;
    private String timeZone;
    private Long tenantId;
}
