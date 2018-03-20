package com.wootion.config;

import com.wootion.spring.webservice.sayhello.service.SayHelloService;
import com.wootion.spring.webservice.user.service.UserWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XZhang
 * @date 2018/3/17 11:30
 * Cxf配置
 */
@Configuration
public class CxfConfig {

    private Bus bus;
    private SayHelloService sayHelloService;
    private UserWebService userWebService;
    public CxfConfig(Bus bus,SayHelloService sayHelloService,UserWebService userWebService){
        this.bus = bus;
        this.sayHelloService = sayHelloService;
        this.userWebService = userWebService;
    }

    /**
     * 接口发布在 UserService和SayHellowService 目录下
     * @return List<Endpoint>
     */
    @Bean
    public List<Endpoint> endpointUser() {
        EndpointImpl endpointSayHellow = new EndpointImpl(bus,sayHelloService);
        endpointSayHellow.publish("/SayHelloService");

        EndpointImpl endpoint = new EndpointImpl(bus,userWebService);
        endpoint.publish("/UserSvice");

        List<Endpoint> endpoints = new ArrayList<>();
        endpoints.add(endpoint);
        endpoints.add(endpointSayHellow);
        return endpoints;
    }
}