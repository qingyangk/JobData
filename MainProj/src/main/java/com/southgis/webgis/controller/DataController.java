package com.southgis.webgis.controller;

import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.entity.CodeEntity;
import com.southgis.webgis.entity.Interface;
import com.southgis.webgis.entity.PageEntity;
import com.southgis.webgis.entity.SearchEntity;
import com.southgis.webgis.service.DataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 数据提取接口
 *
 * @author QingYang
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    DataService dataService;

    /**
     * 查询薪资及地区
     */
    @PostMapping("/querySalary")
    public ResponseInfo querySalary(@RequestBody CodeEntity code) {
        return dataService.querySalary(code);
    }

    /**
     * 展示表格内容--查询职位的信息
     *
     * @param page
     * @return
     */
    @PostMapping("/queryForm")
    public ResponseInfo queryForm(@RequestBody PageEntity page) {
        return dataService.queryForm(page);
    }

    /**
     * 在表格内容中搜索关键字
     *
     * @param model
     * @return
     */
    @PostMapping("/queryAny")
    public ResponseInfo queryAny(@RequestBody SearchEntity model) {
        return dataService.queryAny(model);
    }

    /**
     * 薪资与工作经验关系
     *
     * @return
     */
    @PostMapping("/salaryRe")
    public ResponseInfo salaryRe() {
        return dataService.salaryRe();
    }

    /**
     * 工作经验的计数
     *
     * @return
     */
    @PostMapping("/experienceCo")
    public ResponseInfo experienceCo() {
        return dataService.experienceCo();
    }

    /**
     * 区域中城市的薪资排行
     *
     * @return
     */
    @PostMapping("/salaryAreaTop")
    public ResponseInfo salaryAreaTop(@RequestBody CodeEntity code) {
        return dataService.salaryAreaTop(code);
    }

    /**
     * 行业中的薪资排行
     *
     * @return
     */
    @PostMapping("/sIndustryTop")
    public ResponseInfo sIndustryTop() {
        return dataService.sIndustryTop();
    }

    /**
     * 统计城市中各等级薪资的数量
     *
     * @param code
     * @return
     */
    @PostMapping("/salaryCo")
    public ResponseInfo salaryCo(@RequestBody CodeEntity code) {
        return dataService.salaryCo(code);
    }

    /**
     * 依据id查询该职位的所有信息
     *
     * @param code
     * @return
     */
    @PostMapping("queryAll")
    public ResponseInfo queryAll(@RequestBody CodeEntity code) {
        return dataService.queryAll(code);
    }

    /**
     * 处理数据
     *
     * @param code
     * @return
     */
    @PostMapping("disData")
    public ResponseInfo disData(@RequestBody CodeEntity code) {
        return dataService.disData(code);
    }

    @PostMapping("querySa")
    public ResponseInfo querySa(@RequestBody Interface model) {
        return dataService.querySa(model);
    }
}
