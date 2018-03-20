package com.wootion.spring.webservice.user.service;

import com.wootion.spring.user.entity.UserEntity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

/**
 * 暴露服务名称 UserService
 * 命名空间,一般是接口的包名倒序
 * @author XZhang
 * @date 2018/3/17 11:50
 */
@WebService(name="UserWebService",
        targetNamespace = "http://service.user.webservice.spring.wootion.com")
public interface UserWebService {

    /**
     * 接口方法{查询用户信息}
     * @param userEntity 查询条件
     * @return UserEntity
     */
    @WebMethod
    List<UserEntity> getUser(UserEntity userEntity);
}
