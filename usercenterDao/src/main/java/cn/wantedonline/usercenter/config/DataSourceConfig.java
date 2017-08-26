package cn.wantedonline.usercenter.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangcheng on 26/08/2017.
 */
@Repository
public class DataSourceConfig {

    @Resource(name = "sharding_0")
    private DruidDataSource sharding_0;
    @Resource(name = "sharding_1")
    private DruidDataSource sharding_1;

}
