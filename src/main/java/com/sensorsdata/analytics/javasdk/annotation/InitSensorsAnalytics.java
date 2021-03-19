package com.sensorsdata.analytics.javasdk.annotation;

import com.sensorsdata.analytics.javasdk.SensorsAnalytics;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InitSensorsAnalytics {
    // 从配置文件初始化
    String configPath() default "";

    // 从代码中写死的配置初始化
    Type type() default Type.FILE;

    // ConcurrentLoggingConsumer
    String filenamePrefix() default "";
    int bufferSize() default 8192;
    String lockFileName() default "";
    SensorsAnalytics.LogSplitMode splitMode() default SensorsAnalytics.LogSplitMode.DAY;

    // BatchConsumer && DebugConsumer
    String serverUrl() default "";
    int bulkSize() default 50;
    int maxCacheSize() default 0;
    boolean throwException() default false;
    boolean writeData() default false;

    enum Type{
        FILE, BATCH, DEBUG
    }
}
