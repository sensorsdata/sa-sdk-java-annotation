package com.sensorsdata.analytics.javasdk;

import com.sensorsdata.analytics.javasdk.annotation.InitSensorsAnalytics;
import org.apache.http.util.TextUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConfigOptions {
    // 从代码中写死的配置初始化
    private InitSensorsAnalytics.Type type = InitSensorsAnalytics.Type.FILE;

    // ConcurrentLoggingConsumer
    private String filenamePrefix = "";
    private int bufferSize = 8192;
    private String lockFileName = "";
    private SensorsAnalytics.LogSplitMode splitMode = SensorsAnalytics.LogSplitMode.DAY;

    // BatchConsumer && DebugConsumer
    private String serverUrl = "";
    private int bulkSize = 50;
    private int maxCacheSize = 0;
    private boolean throwException = false;
    private boolean writeData = false;

    private ConfigOptions(InitSensorsAnalytics annotation){
        this.type = annotation.type();
        this.filenamePrefix = annotation.filenamePrefix();
        this.bufferSize = annotation.bufferSize();
        this.lockFileName = annotation.lockFileName();
        this.splitMode = annotation.splitMode();
        this.serverUrl = annotation.serverUrl();
        this.bulkSize = annotation.bulkSize();
        this.maxCacheSize = annotation.maxCacheSize();
        this.throwException = annotation.throwException();
        this.writeData = annotation.writeData();
    }

    public ConfigOptions(){}

    public static ConfigOptions build(InitSensorsAnalytics annotation) throws FileNotFoundException {
        if(!TextUtils.isBlank(annotation.configPath())){
            Yaml yaml = new Yaml(new Constructor(ConfigOptions.class));
            InputStream inputStream = new FileInputStream(new File(annotation.configPath()));
            return yaml.load(inputStream);
        }else{
            return new ConfigOptions(annotation);
        }
    }

    public InitSensorsAnalytics.Type getType() {
        return type;
    }

    public void setType(InitSensorsAnalytics.Type type) {
        this.type = type;
    }

    public String getFilenamePrefix() {
        return filenamePrefix;
    }

    public void setFilenamePrefix(String filenamePrefix) {
        this.filenamePrefix = filenamePrefix;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public String getLockFileName() {
        return lockFileName;
    }

    public void setLockFileName(String lockFileName) {
        this.lockFileName = lockFileName;
    }

    public SensorsAnalytics.LogSplitMode getSplitMode() {
        return splitMode;
    }

    public void setSplitMode(SensorsAnalytics.LogSplitMode splitMode) {
        this.splitMode = splitMode;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getBulkSize() {
        return bulkSize;
    }

    public void setBulkSize(int bulkSize) {
        this.bulkSize = bulkSize;
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public void setThrowException(boolean throwException) {
        this.throwException = throwException;
    }

    public boolean isWriteData() {
        return writeData;
    }

    public void setWriteData(boolean writeData) {
        this.writeData = writeData;
    }

    @Override
    public String toString() {
        return "ConfigOptions{" +
                ", type=" + type +
                ", filenamePrefix='" + filenamePrefix + '\'' +
                ", bufferSize=" + bufferSize +
                ", lockFileName='" + lockFileName + '\'' +
                ", splitMode=" + splitMode +
                ", serverUrl='" + serverUrl + '\'' +
                ", bulkSize=" + bulkSize +
                ", maxCacheSize=" + maxCacheSize +
                ", throwException=" + throwException +
                ", writeData=" + writeData +
                '}';
    }
}
