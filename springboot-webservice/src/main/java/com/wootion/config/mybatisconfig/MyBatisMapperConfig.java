package com.wootion.config.mybatisconfig;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisMapperConfig
 * mapper的映射配置
 * @author Admin
 * @date 2017-10-13
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperConfig {
    @Bean(name = "MapperScannerConfigurer")
    public static MapperScannerConfigurer mapperScannerConfigurer() {

        MapperScannerConfigurer conf= new MapperScannerConfigurer();
        conf.setBasePackage("com.wootion.spring.**.mapper");
        conf.setSqlSessionFactoryBeanName("SqlSessionFactory");
        conf.setAnnotationClass(BootRepository.class);

        return conf;
    }
}