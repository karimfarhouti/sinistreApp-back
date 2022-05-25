package com.avidea.sinistreapp.web.rest;

import com.avidea.sinistreapp.services.FileUploadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileStorageResource {

    private static final Logger log = LoggerFactory.getLogger(FileStorageResource.class);
    private final FileUploadServiceImpl fileUploadService;

    public FileStorageResource(FileUploadServiceImpl fileUploadService) { this.fileUploadService = fileUploadService; }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadClaimImage(MultipartFile file) {
        try {
            String imageUrl = fileUploadService.uploadFile(file);
            return ResponseEntity.ok().body(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
