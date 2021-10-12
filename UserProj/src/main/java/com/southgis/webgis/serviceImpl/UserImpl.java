package com.southgis.webgis.serviceImpl;

import com.southgis.webgis.entity.User;
import com.southgis.webgis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(UserImpl.SERVICE_BEAN_NAME)
public class UserImpl implements UserService{

    public final static String SERVICE_BEAN_NAME = "UserService";

    public User saveUser(String module){

        return null;
    }
}
