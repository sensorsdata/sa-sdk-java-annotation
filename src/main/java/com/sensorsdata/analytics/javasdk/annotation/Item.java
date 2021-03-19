package com.sensorsdata.analytics.javasdk.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Item {
    Type type() default Type.SET;
    String itemType();
    String itemId();
    Property[] properties() default @Property(key="");
    boolean includeParams() default true;
    boolean flush() default false;

    enum Type{
        SET, DELETE
    }
}
