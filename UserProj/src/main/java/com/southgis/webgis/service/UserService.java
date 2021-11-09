package com.southgis.webgis.service;

import com.southgis.webgis.entity.LoginInfo;
import com.southgis.webgis.entity.User;
import com.southgis.webgis.Response.ResponseInfo;
import java.io.Serializable;

/**
 * 用户信息操作
 * @author QingYang
 */
public interface UserService extends Serializable {

    /**
     * 保存用户信息
     * @param module
     * @return
     */
    ResponseInfo saveUser(User module);

    /**
     * 查询用户信息
     * @return
     */
    ResponseInfo queryUser(LoginInfo loginInfo);
}
