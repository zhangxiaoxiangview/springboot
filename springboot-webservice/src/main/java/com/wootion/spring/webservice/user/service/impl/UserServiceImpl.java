package com.wootion.spring.webservice.user.service.impl;

import com.wootion.spring.user.entity.UserEntity;
import com.wootion.spring.user.mapper.UserMapper;
import com.wootion.spring.webservice.user.service.UserWebService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

/**
 * 服务名与接口服务名一致
 * 包名倒叙，并且和接口定义保持一致
 * 包名
 * @author XZhang
 * @date 2018/3/17 11:56
 */
@Service("UserService")
@WebService(serviceName = "UserWebService"
        ,targetNamespace = "http://service.user.webservice.spring.wootion.com"
        ,endpointInterface = "com.wootion.spring.webservice.user.service.UserWebService")
@Component
public class UserServiceImpl implements UserWebService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserEntity> getUser(String userEntity) {
        List<UserEntity> user = null;
        UserEntity users = new UserEntity();
        try {
            users.setUsername(userEntity);
            user = userMapper.getList(users);
        } catch (Exception e) {
            logger.error("查询异常！");
        }finally {
            return  user ;
        }
    }
}