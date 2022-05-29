package com.avidea.sinistreapp.services;

import com.avidea.sinistreapp.config.MinioConfiguration;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    private final MinioConfiguration minioConfiguration;
    private final MinioClient minioClient;


    public FileUploadServiceImpl(MinioConfiguration minioConfiguration, MinioClient minioClient) {
        this.minioConfiguration = minioConfiguration;
        this.minioClient = minioClient;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        PutObjectArgs args;
        try {
            args = PutObjectArgs.builder()
                    .bucket(minioConfiguration.getBucketName())
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            minioClient.putObject(args);
        } catch (Exception e) {
            log.error("Exception message: {}", e.getMessage());
        }
        return fileName;
    }

    @Override
    public byte[] downloadFile(String fileUrl) {
        try {
            InputStream obj = minioClient.getObject(GetObjectArgs.builder().bucket(minioConfiguration.getBucketName()).object(fileUrl).build());
            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            log.error("Exception message: {}", e.getMessage());
        }
        return new byte[0];
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioConfiguration.getBucketName()).object(fileName).build());
        } catch (Exception e) {
            log.error("Exception message: {}", e.getMessage());
        }
    }
}
