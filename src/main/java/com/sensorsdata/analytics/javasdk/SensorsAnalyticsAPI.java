package com.sensorsdata.analytics.javasdk;

import com.sensorsdata.analytics.javasdk.annotation.InitSensorsAnalytics;
import org.apache.http.util.TextUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class SensorsAnalyticsAPI {
    private static SensorsAnalytics sa;

    public synchronized static void startWithAnnotation(InitSensorsAnalytics annotation) throws FileNotFoundException {
        if(sa == null){
            ConfigOptions configOptions = ConfigOptions.build(annotation);
            if(configOptions.getType() == InitSensorsAnalytics.Type.FILE){
                try {
                    sa = new SensorsAnalytics(
                            new SensorsAnalytics.ConcurrentLoggingConsumer(
                                    configOptions.getFilenamePrefix(),
                                    // 注解默认值无法配置成 null，只能给空字符串作为默认值。此处需要将空字符串处理成默认初始化的值 null。
                                    TextUtils.isBlank(configOptions.getLockFileName())? null: configOptions.getLockFileName(),
                                    configOptions.getBufferSize(),
                                    configOptions.getSplitMode()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (configOptions.getType() == InitSensorsAnalytics.Type.BATCH){
                sa = new SensorsAnalytics(
                        new SensorsAnalytics.BatchConsumer(
                                configOptions.getServerUrl(),
                                configOptions.getBulkSize(),
                                configOptions.getMaxCacheSize(),
                                configOptions.isThrowException()
                        ));
            }else{
                sa = new SensorsAnalytics(
                        new SensorsAnalytics.DebugConsumer(
                                configOptions.getServerUrl(),
                                configOptions.isWriteData()
                        ));
            }
        }
    }

    public synchronized static SensorsAnalytics sharedInstance(){
        return sa != null ? sa : new SensorsAnalytics(new ConsumerEmptyImplementation());
    }

    private static class ConsumerEmptyImplementation implements SensorsAnalytics.Consumer{

        @Override
        public void send(Map<String, Object> map) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() {

        }
    }
}
