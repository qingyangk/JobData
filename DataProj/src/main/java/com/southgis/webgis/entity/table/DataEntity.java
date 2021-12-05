package com.southgis.webgis.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
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
    public String salary;
    //public String require;
    //public String experience;
    public String type;
    public String time;


}
