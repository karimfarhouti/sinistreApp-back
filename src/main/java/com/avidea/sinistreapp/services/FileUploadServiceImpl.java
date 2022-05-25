package com.avidea.sinistreapp.services;

import com.avidea.sinistreapp.config.MinioConfiguration;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final MinioConfiguration minioConfiguration;
    private final MinioClient minioClient;


    public FileUploadServiceImpl(MinioConfiguration minioConfiguration, MinioClient minioClient) {
        this.minioConfiguration = minioConfiguration;
        this.minioClient = minioClient;
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        final String fileName = file.getOriginalFilename();
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfiguration.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(args);
        return minioConfiguration.getEndpoint() + "/" + minioConfiguration.getBucketName() + "/" + fileName;
    }
}
