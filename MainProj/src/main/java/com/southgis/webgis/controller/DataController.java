package com.southgis.webgis.controller;

import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.entity.DataType;
import com.southgis.webgis.service.DataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    DataService dataService;

    /**
     * 查询薪资及地区
     */
    @PostMapping("/querySalary")
    public ResponseInfo querySalary(@RequestBody DataType code){
        return dataService.querySalary(code);
    }
}
