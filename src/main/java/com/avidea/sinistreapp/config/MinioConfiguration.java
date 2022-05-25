package com.avidea.sinistreapp.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio", ignoreUnknownFields = false)
public class MinioConfiguration {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private int port;

    private boolean secure;

    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder()
                .credentials(accessKey, secretKey)
                .endpoint(endpoint, port, secure)
                .build();
    }

    public String getEndpoint() { return endpoint; }

    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public String getAccessKey() { return accessKey; }

    public void setAccessKey(String accessKey) { this.accessKey = accessKey; }

    public String getSecretKey() { return secretKey; }

    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public String getBucketName() { return bucketName; }

    public void setBucketName(String bucketName) { this.bucketName = bucketName; }

    public int getPort() { return port; }

    public void setPort(int port) { this.port = port; }

    public boolean isSecure() { return secure; }

    public void setSecure(boolean secure) { this.secure = secure; }
}
