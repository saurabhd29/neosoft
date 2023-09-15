package com.file.FileUpload.beans.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileRequestBean{
    private MultipartFile multipartFile;
    private String directory;
    private HttpServletRequest request;
    private HttpServletResponse response;
}
