package com.avidea.sinistreapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadFile(MultipartFile file) throws Exception;

    byte[] downloadFile(String fileUrl) throws Exception;

    void deleteFile(String fileName) throws Exception;
}
