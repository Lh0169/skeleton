package com.skeleton.module.file.service.storage;

import com.skeleton.module.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class LocalStorageService implements FileService {

    @Value("${file.storage.path:./uploads}")
    private String basePath;

    @Override
    public String upload(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path target = Paths.get(basePath, filename);
            Files.createDirectories(target.getParent());
            Files.copy(file.getInputStream(), target);
            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    @Override
    public byte[] download(String path) {
        try { return Files.readAllBytes(Paths.get(path)); }
        catch (IOException e) { throw new RuntimeException("文件下载失败", e); }
    }

    @Override
    public void delete(String path) {
        try { Files.deleteIfExists(Paths.get(path)); }
        catch (IOException e) { throw new RuntimeException("文件删除失败", e); }
    }
}
