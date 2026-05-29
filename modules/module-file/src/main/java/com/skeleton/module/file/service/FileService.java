package com.skeleton.module.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file);
    byte[] download(String path);
    void delete(String path);
}
