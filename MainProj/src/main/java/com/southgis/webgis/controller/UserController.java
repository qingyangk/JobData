package com.southgis.webgis.controller;

import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.entity.LoginInfo;
import com.southgis.webgis.entity.User;
import com.southgis.webgis.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 保存用户信息
     * @param module
     * @return
     */
    @PostMapping("/saveuser")
    public ResponseInfo saveUser(@RequestBody User module){
        return userService.saveUser(module);
    }

     /**
     *查询用户信息，传入用户名和密码
     * @param loginInfo
     * @return
     */
    @PostMapping("/queryuser")
    public ResponseInfo queryUser(@RequestBody LoginInfo loginInfo){
        return userService.queryUser(loginInfo);
    }
}
