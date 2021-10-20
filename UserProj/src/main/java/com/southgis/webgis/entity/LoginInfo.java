package com.southgis.webgis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginInfo {
    /**
     * 用户名
     */
    @JsonProperty("username")
    public String username;

    /**
     * 用户密码
     */
    @JsonProperty("password")
    public String password;
}
