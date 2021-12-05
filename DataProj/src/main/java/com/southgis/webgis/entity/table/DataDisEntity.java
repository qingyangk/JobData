package com.southgis.webgis.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("jobdata_dispose")
public class DataDisEntity {

    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    @TableField("salary_txt")
    public String salarytxt;

    @TableField("salarycount")
    public int salaryCo;
}
