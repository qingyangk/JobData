package com.southgis.webgis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 对应旧数据表
 */
@TableName("jobdata_old")
@Data
public class OldEntity {
    @TableId(value = "OBJECTID", type = IdType.AUTO)
    public Integer id;

    @TableId("EXPERENCE")
    public String experience;

    @TableId("AVG_SALARY")
    public String salary;
}
