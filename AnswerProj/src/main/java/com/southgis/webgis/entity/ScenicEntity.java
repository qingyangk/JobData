package com.southgis.webgis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("scenic")
@Data
public class ScenicEntity {

    public String name;
    public String diqu;
    public String dengji;
}
