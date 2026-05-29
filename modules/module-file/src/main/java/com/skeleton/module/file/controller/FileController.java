package com.skeleton.module.file.controller;

import com.skeleton.common.core.result.Result;
import com.skeleton.module.file.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件管理")
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        return Result.success(fileService.upload(file));
    }

    @Operation(summary = "删除文件")
    @DeleteMapping
    public Result<Void> delete(@RequestParam String path) {
        fileService.delete(path);
        return Result.success();
    }
}
