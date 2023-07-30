package com.cloud.S3.SimpleStorageService.controller;

import com.cloud.S3.SimpleStorageService.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class StorageController {

    @Autowired
    public StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file")MultipartFile file){
        return ResponseEntity.ok(storageService.uploadFile(file));
    }
    @GetMapping("/downlaod/{filename}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("filename") String fileName) throws IOException {
    byte[] bytes = storageService.downloadFile(fileName);
    ByteArrayResource resource = new ByteArrayResource(bytes);

        return ResponseEntity
                .ok()
                .contentLength(bytes.length)
                .header("Content-type","application/octet-stream")
                .header("Content-disposition","attachment; fileName=\""+fileName+"\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable("filename") String fileName){
        return new ResponseEntity<>(storageService.deleteFile(fileName), HttpStatus.OK);
    }
}
