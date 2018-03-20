package com.wootion.config.mybatisconfig;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * mybatis配置
 * @author admin
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:dataSource.properties")
public class MyBatisConfig {

    private static Logger logger = LogManager.getLogger(MyBatisConfig.class);

    @Bean(name = "MyBatisProperties")
    @ConfigurationProperties(prefix ="jdbc.mybatis")
    public MybatisProperties myBatisProperties() {
        return new MybatisProperties();
    }

    private DataSource dataSource;

    @Autowired
    public MyBatisConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }
    /**
     * 配置factory
     * @param myBatisProperties mybatis配置属性
     * @return SqlSessionFactory
     * @throws Exception 抛出异常
     */
    @Bean(name = "SqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("MyBatisProperties") MybatisProperties myBatisProperties) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            // 包扫描域对象
            bean.setTypeAliasesPackage(myBatisProperties.getTypeAliasesPackage());
            //包扫描处理器
            bean.setTypeHandlersPackage(myBatisProperties.getTypeHandlersPackage());

            // 分页插件
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();

            MybatisProperties.PageHelperProperties page = myBatisProperties.getPage();
            if (StringUtils.isNotBlank(page.getDialect())) {
                properties.setProperty("dialect", page.getDialect());
            }
            // <!-- 该参数默认为false -->
            // <!-- 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用 -->
            // <!-- 和startPage中的pageNum效果一样-->
            if (StringUtils.isNotBlank(page.getOffsetAsPageNum())) {
                properties.setProperty("offsetAsPageNum", page.getOffsetAsPageNum());
            }

            // <!-- 该参数默认为false -->
            // <!-- 设置为true时，使用RowBounds分页会进行count查询 -->
            if (StringUtils.isNotBlank(page.getRowBoundsWithCount())) {
                properties.setProperty("rowBoundsWithCount", page.getRowBoundsWithCount());
            }

            // <!-- 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果 -->
            // <!-- （相当于没有执行分页查询，但是返回结果仍然是Page类型）-->
            if (StringUtils.isNotBlank(page.getPageSizeZero())) {
                properties.setProperty("pageSizeZero", page.getPageSizeZero());
            }

            // <!-- 3.3.0版本可用 - 分页参数合理化，默认false禁用 -->
            // <!-- 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页 -->
            // <!-- 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据 -->
            if (StringUtils.isNotBlank(page.getReasonable())) {
                properties.setProperty("reasonable", page.getReasonable());
            }

            // <!-- 3.5.0版本可用 - 为了支持startPage(Object params)方法 -->
            // <!-- 增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值 -->
            // <!-- 可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值 -->
            // <!-- 不理解该含义的前提下，不要随便复制该配置 -->
            // <!-- properties.setProperty("params", "pageNum=start;pageSize=limit;") -->
            // <!--支持通过Mapper接口参数来传递分页参数-->
            if (StringUtils.isNotBlank(page.getSupportMethodsArguments())) {
                properties.setProperty("supportMethodsArguments", page.getSupportMethodsArguments());
            }

            // <!-- always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page -->
            if (StringUtils.isNotBlank(page.getReturnPageInfo())) {
                properties.setProperty("returnPageInfo", page.getReturnPageInfo());
            }

            pageHelper.setProperties(properties);
            // 添加插件
            bean.setPlugins(new Interceptor[] { pageHelper });

            // 添加XML目录
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            try {
                bean.setMapperLocations(resolver.getResources(myBatisProperties.getMapperLocations()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bean.getObject();
    }
    @Bean(name = "SqlSession")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 开启事务
     * @return dataSourceTransactionManager
     * @throws Exception 抛出异常
     */
    @Bean(name = "Transaction")
    public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}