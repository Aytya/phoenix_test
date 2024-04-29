package com.example.phoenix_test.config;

import com.example.phoenix_test.repository.UserRepository;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationConfig {
    private final UserRepository repository;
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(this.endpoint).credentials(this.accessKey, this.secretKey).build();
    }
}
