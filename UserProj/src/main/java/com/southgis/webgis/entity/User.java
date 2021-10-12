package com.southgis.webgis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    public Integer id;
    public String username;
    public String password;
    public Integer age;
    public String sex;
    public String address;

}
