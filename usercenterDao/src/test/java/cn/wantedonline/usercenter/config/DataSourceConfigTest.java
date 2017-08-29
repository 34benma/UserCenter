package cn.wantedonline.usercenter.config;

import cn.wantedonline.usercenter.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * Created by louiswang on 17/8/28.
 */
public class DataSourceConfigTest extends BaseTest {

    @Test
    public void testGetShardingDataSource() {
        DataSource dataSource = DataSourceConfig.userinfoDataSource;
        System.out.println("OK");

    }
}
