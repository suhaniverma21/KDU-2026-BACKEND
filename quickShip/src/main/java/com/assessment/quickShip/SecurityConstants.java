package com.assessment.quickShip;

public class SecurityConstants {
    private SecurityConstants() {}

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    //public static final String ROLE_BASIC = "ROLE_BASIC";
    //public static final String ROLE_ADMIN = "ROLE_ADMIN";
    //public static final String ROLE_MEMBER = "ROLE_MEMBER";
    //public static final String ROLE_LIBRARIAN = "ROLE_LIBRARIAN";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_DRIVER = "ROLE_DRIVER";
    public static final long JWT_EXPIRATION = 1000 * 60 * 60; // 1 hour
}
