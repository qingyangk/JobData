package com.southgis.webgis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("jobdata")
@Data
public class DataEntity {

    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;
    public String company;
    public String position;
    public String region;
    public String salary;
    //public String require;
    //public String experience;
    public String type;
    public String time;

//    public Integer getId() {
//        return id;
//    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getRegion() {
        return region;
    }

    public String getSalary() {
        return salary;
    }

//    public String getRequire() {
//        return require;
//    }

//    public String getExperience() {
//        return experience;
//    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }
}
