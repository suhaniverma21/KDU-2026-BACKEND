package org.example;


import java.lang.reflect.Field;

// Assume RangeCheck is defined correctly elsewhere
public class SystemConfig {

    @RangeCheck(min = 1, max = 16)
    private int maxThreads = 8;

    @RangeCheck(min = 100, max = 5000)
    private int timeoutSeconds = 2500;

    public SystemConfig(int maxThreads, int timeoutSeconds) {
        this.maxThreads = maxThreads;
        this.timeoutSeconds = timeoutSeconds;
    }

    // A simple method for logging successful checks
    public static void logSuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }
    private void validate() {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(RangeCheck.class)) {
                    field.setAccessible(true); // access private fields
                    int value = field.getInt(this);
                    RangeCheck range = field.getAnnotation(RangeCheck.class);
                    if (value < range.min() || value > range.max()) {
                        throw new ConfigValidationException(
                                field.getName() + " is out of range [" + range.min() + ", " + range.max() + "]"
                        );
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Validation failed", e);
        }
    }
}

// File: ConfigValidationException.java (Unchecked Exception)
// Student must create this simple custom unchecked exception class
// ...


