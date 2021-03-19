package com.sensorsdata.analytics.javasdk.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Profile {
    Type type() default Type.SET;
    String distinctId() default "";
    boolean isLoginId() default true;
    Property[] properties() default @Property(key="");
    boolean includeParams() default true;
    boolean flush() default false;

    enum Type{
        SET,SET_ONCE,INCREMENT,APPEND
    }
}
