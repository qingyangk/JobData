package com.southgis.webgis.entity.info;

import lombok.Data;


/**
 * 简单查询
 * 表格展示
 * @author QingYang
 */
@Data
public class JobInfo {

    public Integer id;
    public String company;
    public String position;
    public String region;
    public String salarySe;
    public String education;
    public String experience;
    public String size;
    public String time;
    public double x;
    public double y;


}
