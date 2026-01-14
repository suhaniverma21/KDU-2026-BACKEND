package com.springSecurity.hands_on;

public class SecurityConstants {
    private SecurityConstants() {}

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLE_BASIC = "ROLE_BASIC";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final long JWT_EXPIRATION = 1000 * 60 * 60; // 1 hour
}
