package cn.wantedonline.usercenter.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by louiswang on 17/8/25.
 */
public class DataSourceTest {
    private static final String url1 = "jdbc:mysql://172.20.16.134:3306/user0?allowMultiQueries=true&useUnicode=True&characterEncoding=utf-8";
    private static final String url2 = "jdbc:mysql://192.168.17.15:3306/user1?allowMultiQueries=true&useUnicode=True&characterEncoding=utf-8";
    private static final String url3 = "jdbc:mysql://192.168.17.15:3306/user2?allowMultiQueries=true&useUnicode=True&characterEncoding=utf-8";
    public static void main(String[] args) {

        //数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("user0", createDataSource(url1));
        dataSourceMap.put("user1", createDataSource(url2));
        dataSourceMap.put("user2", createDataSource(url3));

        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);

        TableRule userinfoTableRule = TableRule.builder("userinfo")
                .actualTables(
                        Arrays.asList(
                                "userinfo0",
                                "userinfo1")
                ).dataSourceRule(dataSourceRule)
                .build();

        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(userinfoTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("uid", new ModuloDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("uid", new ModuloTableShardingAlgorithm()))
                .build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
        String sql = "SELECT * from userinfo where uid BETWEEN ? and ?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, 10000);
            preparedStatement.setInt(2, 40000);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String value = rs.getString(3);
                System.out.println(value);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据源
     * @return
     */
    private static DataSource createDataSource(String url) {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(url);
        result.setUsername("root");
        result.setPassword("123456");
        return result;
    }
}
