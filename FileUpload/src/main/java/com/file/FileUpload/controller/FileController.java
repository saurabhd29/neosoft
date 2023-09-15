package com.file.FileUpload.controller;

import com.file.FileUpload.beans.request.FileRequestBean;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RequestMapping("/api/v1/file")
@RestController
public class FileController {

    @GetMapping("/hello")
    public String heelo(){
        return "hello";
    }

    @PostMapping(value = "/file-upload")
    public ResponseEntity<?> fileUpload(@RequestParam(value = "file") MultipartFile file){

        String ROOT_DIRECTORY = "D:\\Upload\\";
        Path filePath = Paths.get(ROOT_DIRECTORY);
        try {
            File directory = new File(filePath.toUri());
            if(!directory.exists()){
                directory.mkdirs();
            }
            Files.write(filePath.resolve(Objects.requireNonNull(file.getOriginalFilename()))
                    ,file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(filePath.toString());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile){

        String ROOT_DIRECTORY="D:\\Uploads1\\";
        Path filePath = Paths.get(ROOT_DIRECTORY);

        try {
            File directory = new File(filePath.toUri());
            if(!directory.exists()){
                directory.mkdirs();
            }
            Files.write(filePath.resolve(Objects.requireNonNull(multipartFile.getOriginalFilename()))
                    ,multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/upload1")
    public ResponseEntity<?> upload1(@RequestParam("file") MultipartFile multipartFile){
        String ROOT = "D:\\demo";
        Path filePath = Paths.get(ROOT);
        try {
            File directory = new File(filePath.toUri());
            if(!directory.exists()){
                directory.mkdir();
            }
            Files.write(filePath.resolve(Objects.requireNonNull(multipartFile.getOriginalFilename())),
                    multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(filePath.toString());
    }
}
