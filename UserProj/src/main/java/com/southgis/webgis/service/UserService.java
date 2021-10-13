package com.southgis.webgis.service;

import com.southgis.webgis.entity.User;
import com.southgis.webgis.Response.ResponseInfo;
import java.io.Serializable;

/**
 * 用户信息——对数据库操作
 * @author QingYang
 */
public interface UserService extends Serializable {

    /**
     * 保存用户信息
     * @param module
     * @return
     */
    ResponseInfo saveUser(User module);
}
