package com.sensorsdata.analytics.javasdk.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.CLASS)
public @interface Property {
    String key();
    String value() default "";
}
