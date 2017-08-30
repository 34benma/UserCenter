package cn.wantedonline.usercenter.config;

import cn.wantedonline.common.utils.Iterables;
import cn.wantedonline.usercenter.exception.DaoException;
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
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wangcheng on 26/08/2017.
 */
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private ShardingRule userInfoShardingRule;

    private List<DataSource> dataSources;

    public DataSource userinfoDataSource;

    public DataSourceConfig(List<DataSource> dataSources) throws DaoException {
        this.dataSources = dataSources;
        init();
    }

    private void init() throws DaoException {
        logger.info("===================begin init datasource======================");
        DataSourceRule dataSourceRule = buildDataSourceRule();
        List<TableRule> tableRules = buildTableRules(dataSourceRule);
        userInfoShardingRule = buildUserShardingRule(dataSourceRule, tableRules);
        userinfoDataSource = ShardingDataSourceFactory.createDataSource(userInfoShardingRule);
        logger.info("===================finished init datasource====================");
    }

    /**
     * 创建 数据源规则 后续新增数据源或修改数据源从这里修改
     * @return
     */
    private DataSourceRule buildDataSourceRule() throws DaoException {

        if (CollectionUtils.isEmpty(dataSources)) {
            throw new DaoException("can't init sharding datasource, source dataSource is empty");
        }

        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        Iterables.forEach(dataSources, (index, datasource) -> dataSourceMap.put("ds_"+index, datasource));
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
