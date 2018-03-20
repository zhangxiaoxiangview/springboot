package com.wootion.config.druidconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;

/**
 * 数据源配置
 * @author zhangxiaoxiang
 * @date 2018-03-17
 */
@Configuration
@PropertySource("classpath:dataSource.properties")
public class DruidConfig {

    private static Logger logger = LogManager.getLogger(DruidConfig.class);

    @Bean(value = "myDruidProperties" )
    @ConfigurationProperties(prefix = "spring.druid")
    public DruidProperties myDruidProperties() {
        return new DruidProperties();
    }

    /**
     * 在同样的DataSource中，首先使用被标注的DataSource
     * @return DruidDataSource
     */
    @Bean(value = "druidDB",initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource(@Qualifier("myDruidProperties") DruidProperties myDruidProperties) {

        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setUrl(myDruidProperties.getDbUrl());
            dataSource.setUsername(myDruidProperties.getUsername());
            dataSource.setPassword(myDruidProperties.getPassword());
            dataSource.setDriverClassName(myDruidProperties.getDriverClassName());
            System.out.println("druidDB Start.....");
            System.out.println("[数据库链接类型:"+myDruidProperties.getDbType()+"]");
            System.out.println("[数据库连接地址:"+myDruidProperties.getDbUrl()+"]");
            System.out.println("[数据库连接用户:"+myDruidProperties.getUsername()+"]");
            System.out.println("[数据库连接密码:"+myDruidProperties.getPassword()+"]");
            //configuration
            dataSource.setInitialSize(myDruidProperties.getInitialSize());
            dataSource.setMinIdle(myDruidProperties.getMinIdle());
            dataSource.setMaxActive(myDruidProperties.getMaxActive());
            dataSource.setMaxWait(myDruidProperties.getMaxWait());
            dataSource.setTimeBetweenEvictionRunsMillis(myDruidProperties.getTimeBetweenEvictionRunsMillis());
            dataSource.setMinEvictableIdleTimeMillis(myDruidProperties.getMinEvictableIdleTimeMillis());
            dataSource.setValidationQuery(myDruidProperties.getValidationQuery());
            dataSource.setTestWhileIdle(myDruidProperties.isTestWhileIdle());
            dataSource.setTestOnBorrow(myDruidProperties.isTestOnBorrow());
            dataSource.setTestOnReturn(myDruidProperties.isTestOnReturn());
            dataSource.setPoolPreparedStatements(myDruidProperties.isPoolPreparedStatements());
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(myDruidProperties.getMaxPoolPreparedStatementPerConnectionSize());
            dataSource.setFilters(myDruidProperties.getFilters());
        } catch (SQLException e) {
            logger.error("Database connection exception !");
        }
            return dataSource;
    }
}