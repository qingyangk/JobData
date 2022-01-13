package com.southgis.webgis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ServletComponentScan
@SpringBootApplication
//@ComponentScan(basePackages = {"com.southgis.webgis.service"})
public class WebGisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGisApplication.class, args);
    }

}
