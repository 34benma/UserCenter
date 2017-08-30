package cn.wantedonline.usercenter.config;

import cn.wantedonline.usercenter.exception.DaoException;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by wangcheng on 26/08/2017.
 */
@Configuration
@ComponentScan(basePackages = {"cn.wantedonline.usercenter"})
public class DaoRootConfig {
    private ResourceLoader defaultLoader = new DefaultResourceLoader();

    /**
     * 加载占位符
     * @return
     * @throws IOException
     */
    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        Resource[] propertiesResources = ResourcePatternUtils.getResourcePatternResolver(defaultLoader)
                .getResources("classpath*:*.properties");
        configurer.setLocations(propertiesResources);
        return configurer;
    }

    @Bean
    public DataSource getUserShardingDataSource() throws IOException, DaoException {
        List<DataSource> druidDataSources = Lists.newArrayList(getSharding0DataSource(), getSharding1DataSource());
        DataSourceConfig config = new DataSourceConfig(druidDataSources);
        return config.userinfoDataSource;
    }


    @Bean
    public StatFilter getStatFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(50L);
        filter.setLogSlowSql(false);
        filter.setMergeSql(true);
        return filter;
    }

    @Bean
    public Slf4jLogFilter getSlf4jLogFilter() {
        Slf4jLogFilter filter = new Slf4jLogFilter();
        filter.setStatementExecutableSqlLogEnable(false);
        filter.setDataSourceLogEnabled(false);
        return filter;
    }

    @Bean(name = "sharding_0")
    public DruidDataSource getSharding0DataSource() throws IOException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        Properties druidConfig = PropertiesLoaderUtils.loadProperties(
                defaultLoader.getResource("classpath:sharding_0.properties"));
        dataSource.configFromPropety(druidConfig);
        dataSource.setProxyFilters(Lists.newArrayList(getSlf4jLogFilter(), getStatFilter()));
        return dataSource;

    }

    @Bean(name = "sharding_1")
    public DruidDataSource getSharding1DataSource() throws IOException{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        Properties druidConfig = PropertiesLoaderUtils.loadProperties(
                defaultLoader.getResource("classpath:sharding_1.properties"));
        dataSource.configFromPropety(druidConfig);
        dataSource.setProxyFilters(Lists.newArrayList(getSlf4jLogFilter(), getStatFilter()));
        return dataSource;
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfiger() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("cn.wantedonline.usercenter");
        configurer.setSqlSessionFactoryBeanName("sessionFactory");
        return configurer;
    }

    @Bean(name = "sessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException, DaoException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getUserShardingDataSource());
        bean.setConfigLocation(defaultLoader.getResource("classpath:mybatis.xml"));
        Resource[] mapperResources = ResourcePatternUtils.getResourcePatternResolver(defaultLoader)
                .getResources("classpath*:/mapper/*.xml");
        bean.setMapperLocations(mapperResources);
        return bean;
    }
}
