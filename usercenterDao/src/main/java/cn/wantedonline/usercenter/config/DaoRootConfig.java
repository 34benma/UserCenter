package cn.wantedonline.usercenter.config;

import cn.wantedonline.common.utils.SnowflakeIDWorker;
import cn.wantedonline.usercenter.exception.DaoException;
import cn.wantedonline.usercenter.jedis.JedisTemplate;
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
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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

    @Bean(name = "jedisPool")
    public JedisPool getJedisPool() throws IOException {
        Properties redisConfig = PropertiesLoaderUtils.loadProperties(
                defaultLoader.getResource("classpath:conf.properties"));
        String host = redisConfig.getProperty("redis_ip");
        int port = Integer.valueOf(redisConfig.getProperty("redis_port", "6379"));
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(false);
        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        poolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        //是否启用pool的jmx管理功能, 默认true
        poolConfig.setJmxEnabled(true);
        poolConfig.setJmxNamePrefix("redis_pool");
        //是否启用后进先出, 默认true
        poolConfig.setLifo(true);
        //最大空闲连接数, 默认8个
        poolConfig.setMaxIdle(8);
        //最大连接数, 默认8个
        poolConfig.setMaxTotal(8);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        poolConfig.setMaxWaitMillis(-1);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        poolConfig.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        poolConfig.setMinIdle(0);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        poolConfig.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        poolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
        //在获取连接的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(false);
        //在空闲时检查有效性, 默认false
        poolConfig.setTestWhileIdle(false);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        poolConfig.setTimeBetweenEvictionRunsMillis(-1);
        JedisPool pool = new JedisPool(poolConfig, host, port);
        return pool;
    }

    @Bean(name = "jedisTemplate")
    public JedisTemplate getJedisTemplate() throws IOException {
        return new JedisTemplate(getJedisPool());
    }

    @Bean(name = "snowflakeIDWorker")
    public SnowflakeIDWorker getSnowflakeIDWorker() {
        long workId = 1L;
        return new SnowflakeIDWorker(workId);
    }
}
