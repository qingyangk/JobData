package com.southgis.webgis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField(value = "EXPERENCE")
    public String experience;

    @TableField(value = "AVG_SALARY")
    public String salary;

}
