package com.sensorsdata.analytics.javasdk.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface TrackSignUp {
    String loginId() default "";
    String anonymousId();
    boolean flush() default false;
}
