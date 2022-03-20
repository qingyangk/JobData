package com.southgis.webgis.entity.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("regionjob")
public class RegionJob {
    public int id;
    public String region;
    public double avgsalary;
    public String city;
}
