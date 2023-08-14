package com.dbadmin.classloader.controller;

import com.dbadmin.classloader.utlis.PrintClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    PrintClassLoader loader;

    @GetMapping("/print")
    public String loadClass() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, MalformedURLException {
        loader.printClassLoaders();
        return "Printed";
    }
}
