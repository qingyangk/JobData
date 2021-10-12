package com.southgis.webgis.service;

import com.southgis.webgis.entity.User;
import com.southgis.webgis.mapper.UserMapper;

import javax.annotation.Resource;
import java.io.Serializable;

public interface UserService extends Serializable {

    /**
     * 保存用户信息
     * @param module
     * @return
     */
    User saveUser(String module);
}
