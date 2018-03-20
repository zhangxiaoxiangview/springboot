package com.wootion.spring.webservice.sayhello.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 暴露服务名称 SayHelloService
 * 命名空间,一般是接口的包名倒序
 * @author XZhang
 * @date 2018/3/17 11:30
 */
@WebService(name="SayHelloService",
        targetNamespace = "http://service.user.webservice.spring.wootion.com")
public interface SayHelloService {

    /**
     * test
     * @return String
     */
    @WebMethod
    String sayHello();
}
