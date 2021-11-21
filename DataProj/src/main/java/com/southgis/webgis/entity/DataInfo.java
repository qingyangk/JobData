package com.southgis.webgis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataInfo {

    /**
     * 坐标
     */
    //public String x;

    /**
     * 坐标
     */
    //public String y;

    /**
     * 发布日期
     */
    //public Time time;

    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;
    /**
     * 薪水
     */
    @JsonProperty("value")
    public String salary;

    /**
     * 职位
     */
    @JsonProperty("name")
    public String position;

}
