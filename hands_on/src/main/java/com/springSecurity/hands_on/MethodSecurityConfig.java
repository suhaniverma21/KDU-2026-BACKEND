package com.springSecurity.hands_on;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {
    // Empty, just enables @PreAuthorize, @PostAuthorize, etc.
}
