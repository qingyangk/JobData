package com.southgis.webgis.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cityjob")
public class CityJob {
    @TableId(value = "id", type = IdType.AUTO)
    public int id;
    public String city;
    public double avgsalary;
    public int salaryrank;
}
