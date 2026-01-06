package org.example;


public class Main_Config {
    public static void main(String[] args) {
        // Valid config
        SystemConfig config1 = new SystemConfig(8, 2000);
        System.out.println("Config1 created successfully.");

        // Invalid config (will throw exception)
        SystemConfig config2 = new SystemConfig(20, 50);
        System.out.println("Config2 created successfully.");
    }
}

