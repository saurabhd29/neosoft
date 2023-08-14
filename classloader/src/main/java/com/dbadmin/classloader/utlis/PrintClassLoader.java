package com.dbadmin.classloader.utlis;

import com.dbadmin.classloader.demo.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
@Slf4j
public class PrintClassLoader {
//    @Autowired
//    ClassLoader loader;

    public String printClassLoaders() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, MalformedURLException {

        log.info("Current class loader : {}",PrintClassLoader.class.getClassLoader());

        log.info("DriverMangaer : {}", DriverManager.class.getClassLoader());

        log.info("HashMap : {}", HashMap.class.getClassLoader());

        log.info("ArrayList : {}", ArrayList.class.getClassLoader());
//        CustomClassLoader classLoader = new CustomClassLoader(Arrays.asList(new File("D:\\WorkSpace\\Car\\car.jar").toURI().toURL()));
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };

        Class<?> carClass = loader.loadClass("com.dbadmin.classloader.demo.Car");
        Car someCar = (Car)carClass.newInstance();
        String result = someCar.getCarName();
        log.info("Car type : {}",someCar);
        log.info("Car : {}",result);
        return result;
    }

    public void getJar(String args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {


    }
}
