package com.southgis.webgis.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("jobform")
public class JobEntity {
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    public String company;

    public String position;

    public String region;

    public String experience;

    public String type;

    public String time;

    public double x;

    public double y;

    public String education;

    public String size;

    @TableField("salary")
    public double salarySe;
}
