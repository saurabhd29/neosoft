package com.spring.mongodb.controller;


import com.spring.mongodb.enity.Photo;
import com.spring.mongodb.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RequestMapping("/photo")
@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @PostMapping("/add")
        public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = photoService.addPhoto(title, image);
        return "redirect:/photos/" + id;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/photo-add/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(photo.getImage().getData());
    }
}
