package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)      // available at runtime
@Target(ElementType.FIELD)               // only allowed on fields
public @interface RangeCheck {

    int min();
    int max();
}
