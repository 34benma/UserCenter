package cn.wantedonline.usercenter.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wangcheng on 26/08/2017.
 */
@Repository
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Resource(name = "sharding_0")
    private DataSource sharding_0;
    @Resource(name = "sharding_1")
    private DataSource sharding_1;

    private ShardingRule userInfoShardingRule;

    public static DataSource userinfoDataSource;

    /**
     *
     * @param type TODO 并发场景下的如何做
     * @return 如果没有所指定的类型 则返回 null
     */
    public DataSource getShardingDataSource(ShardingDataSourceType type) {
        switch (type) {
            case USERINFO:
                if (userinfoDataSource == null) {
                    userinfoDataSource = ShardingDataSourceFactory.createDataSource(userInfoShardingRule);
                }
                return userinfoDataSource;
            default:
                break;
        }
        return null;
    }

    @PostConstruct
    private void init() {
        logger.info("===================begin init datasource======================");
        DataSourceRule dataSourceRule = buildDataSourceRule();
        List<TableRule> tableRules = buildTableRules(dataSourceRule);
        userInfoShardingRule = buildUserShardingRule(dataSourceRule, tableRules);
        logger.info("===================finished init datasource====================");
    }

    /**
     * 创建 数据源规则 后续新增数据源或修改数据源从这里修改
     * @return
     */
    private DataSourceRule buildDataSourceRule() {
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("ds_0", sharding_0);
        dataSourceMap.put("ds_1", sharding_1);
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
        return dataSourceRule;
    }

    /**
     * 构建所有sharding表规则列表
     * @return
     */
    private List<TableRule> buildTableRules(DataSourceRule dataSourceRule) {
        List<TableRule> rtnList = Lists.newArrayList();
        rtnList.add(buildUserInfoTableRule(dataSourceRule));
        return rtnList;
    }

    /**
     *
     * @param dataSourceRule
     * @return
     */
    private TableRule buildUserInfoTableRule(DataSourceRule dataSourceRule) {
        return TableRule.builder("userinfo")
                .actualTables(Arrays.asList("userinfo0", "userinfo1"))
                .dataSourceRule(dataSourceRule)
                .build();
    }

    private ShardingRule buildUserShardingRule(DataSourceRule dataSourceRule, List<TableRule> tableRules) {
        return ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(tableRules)
                .databaseShardingStrategy(
                        new DatabaseShardingStrategy("uid", new UserDatabaseShardingAlgorithm())
                )
                .tableShardingStrategy(
                        new TableShardingStrategy("uid", new UserInfoTableShardingAlgorithm())
                )
                .build();
    }

}
