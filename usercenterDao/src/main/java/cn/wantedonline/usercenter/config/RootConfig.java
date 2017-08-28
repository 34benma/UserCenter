package cn.wantedonline.usercenter.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by wangcheng on 26/08/2017.
 */
@Configuration
@ComponentScan(basePackages = {"cn.wantedonline.usercenter"})
public class RootConfig {
    @Bean
    public MapperScannerConfigurer getMapperScannerConfiger() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("cn.wantedonline.usercenter");
        configurer.setSqlSessionFactoryBeanName("sessionFactory");
        return configurer;
    }

    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean() {
        ResourceLoader loader = new DefaultResourceLoader();

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(DataSourceConfig.userinfoDataSource);
        bean.setConfigLocation(loader.getResource("classpath:mybatis.xml"));
        return bean;
    }
}
