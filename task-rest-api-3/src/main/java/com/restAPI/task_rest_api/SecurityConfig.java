package com.restAPI.task_rest_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // enables @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for Postman
                .authorizeHttpRequests(auth -> auth
                        // DELETE /books/{id} → only ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                        // GET endpoints → public
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                        // POST endpoint → public
                        .requestMatchers(HttpMethod.POST, "/books").permitAll()
                        // PUT endpoint → require authentication (optional, can change to permitAll())
                        .requestMatchers(HttpMethod.PUT, "/books/**").authenticated()
                        // any other request → authenticated
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // enable basic auth

        return http.build();
    }
}
