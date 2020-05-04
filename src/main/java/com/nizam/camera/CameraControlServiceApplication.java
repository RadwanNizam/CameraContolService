package com.nizam.camera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nizam.camera"})
public class CameraControlServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CameraControlServiceApplication.class, args);
    }
}
