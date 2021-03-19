package com.sensorsdata.analytics.javasdk.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Track {
    String distinctId() default "";
    boolean isLoginId() default true;
    String eventName() default "";
    Property[] properties() default @Property(key="");
    boolean includeParams() default true;
    boolean flush() default false;
}
