package com.s3upload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Clinet;

    public String uploadFile(MultipartFile file)
    {
        File convertedFile = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Clinet.putObject(new PutObjectRequest(bucketName,fileName,convertedFile));
        convertedFile.delete();
        return "File uploaded "+fileName;
    }

    public byte[] downloadFile(String fileName) throws IOException {
        S3Object object = s3Clinet.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = object.getObjectContent();
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        return byteArray;
    }

    public String deleteFile(String fileName){
        s3Clinet.deleteObject(bucketName,fileName);
        return fileName+" removed";
    }

    private File convertMultiPartFileToFile(MultipartFile file){
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Error converting multipartFile ti file ",e);
        }
        return convertedFile;
    }

}
