package com.southgis.webgis.serviceImpl;

import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.User;
import com.southgis.webgis.mapper.UserMapper;
import com.southgis.webgis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service(UserImpl.SERVICE_BEAN_NAME)
public class UserImpl implements UserService{

    public final static String SERVICE_BEAN_NAME = "UserService";

    @Resource
    UserMapper userMapper;

    public ResponseInfo saveUser(User module){
        userMapper.insert(module);
        return new ResponseInfo(EnumErrCode.OK,"用户信息入库成功","");
    }
}
