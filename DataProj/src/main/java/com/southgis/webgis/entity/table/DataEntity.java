package com.southgis.webgis.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("jobdata")
public class DataEntity {

    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    public String company;

    public String position;

    public String region;

    public String requirement;

    public String experience;

    public String type;

    public String time;

    @TableField("avg_salary")
    public String salary;

    public double x;

    public double y;

    public String industry;

    public String introduce;

    public String treat;

    public String education;

    public String size;

    public String address;

    @TableField("salary")
    public String salarySe;
}
