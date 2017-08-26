package cn.wantedonline.usercenter.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by wangcheng on 26/08/2017.
 */
@Configuration
public class RootConfig {

    @Bean
    public ClassPathResource getConfResource() {
        return new ClassPathResource("classpath*:conf.properties");
    }

    @Bean
    public PropertyPlaceholderConfigurer getConfig(ClassPathResource resource) {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocations(resource);
        return configurer;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource shardingA() {
        DruidDataSource shardingA = new DruidDataSource();
        return shardingA;
    }
}
