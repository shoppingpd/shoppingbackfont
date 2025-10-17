package com.real.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/files")
public class imgloader {

    private final String uploadDir = "uploads";

    // 上傳/更換檔案
    @PostMapping("/upload")
    public ResponseEntity<String> uploadOrReplaceFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "oldFileName", required = false) String oldFileName
    ) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // 如果有傳入舊檔名，先刪掉舊檔
            if (oldFileName != null && !oldFileName.isEmpty()) {
                Path oldPath = Paths.get(uploadDir, oldFileName);
                Files.deleteIfExists(oldPath);
            }

            // 產生新檔名，避免同名覆蓋問題
            String originalName = file.getOriginalFilename();
            String ext = "";
            if (originalName != null && originalName.contains(".")) {
                ext = originalName.substring(originalName.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID() + ext;
            Path filePath = Paths.get(uploadDir, newFileName);
            Files.write(filePath, file.getBytes());

            String fileUrl = "/files/view/" + newFileName;
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上傳失敗");
        }
    }

    // 顯示圖片
    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir, filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
