package com.wootion.spring.webservice.sayhello.service.impl;

import com.wootion.spring.webservice.sayhello.service.SayHelloService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * 服务名与接口服务名一致
 * 包名倒叙，并且和接口定义保持一致
 * 包名
 * @author XZhang
 * @date 2018/3/17 11:34
 */
@WebService(serviceName = "SayHellowService"
        ,targetNamespace = "http://service.user.webservice.spring.wootion.com"
        ,endpointInterface = "com.wootion.spring.webservice.sayhello.service.SayHelloService")
@Component
public class SayHelloServiceImpl implements SayHelloService{

    @Override
    public String sayHello() {
        return "Hello Word!";
    }
}
