package com.southgis.webgis.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.User;
import com.southgis.webgis.mapper.UserMapper;
import com.southgis.webgis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service(UserImpl.SERVICE_BEAN_NAME)
public class UserImpl implements UserService {

    public final static String SERVICE_BEAN_NAME = "UserService";

    @Resource
    UserMapper userMapper;

    public ResponseInfo saveUser(User module) {
        userMapper.insert(module);
        return new ResponseInfo(EnumErrCode.OK, "用户信息入库成功", "");
    }

    public ResponseInfo queryUser(User module) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", module.username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            queryWrapper.like("password",module.password).lt("username",module.username);
            List<User> userList = userMapper.selectList(queryWrapper);
            if (!userList.isEmpty()){
                return new ResponseInfo(EnumErrCode.OK,"账号密码正确");
            }else {
                return new ResponseInfo(EnumErrCode.BusinessError,"密码错误");
            }
        } else {
            return new ResponseInfo(EnumErrCode.BusinessError, "查询无记录");
        }
    }
}
