package com.southgis.webgis.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.LoginInfo;
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

    /**
     * 保存用户信息
     *
     * @param module
     * @return
     */
    public ResponseInfo saveUser(User module) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        try {
            queryWrapper.eq("username", module.username);
            List<User> users = userMapper.selectList(queryWrapper);
            //判断账号是否存在，存在则直接返回
            if (!users.isEmpty()) {
                return new ResponseInfo(EnumErrCode.BusinessError, "账号已存在");
            } else {
                userMapper.insert(module);
                return new ResponseInfo(EnumErrCode.OK, "用户信息入库成功");
            }
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.BusinessError, ex.getMessage());
        }
    }

    /**
     * 查询是否存在账户
     *
     * @param loginInfo
     * @return
     */
    //, HttpSession session 拦截器
    public ResponseInfo queryUser(LoginInfo loginInfo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        try {
            queryWrapper.eq("username", loginInfo.username);
            List<User> users = userMapper.selectList(queryWrapper);
            if (!users.isEmpty()) {
                queryWrapper.eq("password", loginInfo.password);//lt("username",loginInfo.username);
                users = userMapper.selectList(queryWrapper);
                if (!users.isEmpty()) {
                    return new ResponseInfo(EnumErrCode.OK, "账号密码正确", users);
                } else {
                    return new ResponseInfo(EnumErrCode.BusinessError, "密码错误");
                }
            } else {
                return new ResponseInfo(EnumErrCode.BusinessError, "查询无账号记录");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.BusinessError, ex.getMessage());
        }
    }
}
