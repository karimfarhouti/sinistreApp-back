package com.avidea.sinistreapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadFile(MultipartFile file) throws Exception;
}
