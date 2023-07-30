package com.cloud.S3.SimpleStorageService.service;

import com.amazonaws.services.s3.AmazonS3;
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

@Slf4j
@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucket;

    @Autowired
    private AmazonS3 s3;

    public String uploadFile(MultipartFile file){
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        log.info("Bucket {}",bucket);
        s3.putObject(bucket,fileName,fileObj);
        fileObj.delete();
        return "File Uploaded : "+fileName;

    }

    public byte[] downloadFile(String fileName){
        S3Object S3Object= s3.getObject(bucket,fileName);
        S3ObjectInputStream s3ObjectInputStream= S3Object.getObjectContent();
        byte[] content=null;
        try{
            content  = IOUtils.toByteArray(s3ObjectInputStream);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return content;
    }

    public String deleteFile(String fileName){
        s3.deleteObject(bucket,fileName);
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
