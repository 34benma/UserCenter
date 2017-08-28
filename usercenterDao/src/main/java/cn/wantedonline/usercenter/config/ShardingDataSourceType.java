package cn.wantedonline.usercenter.config;

/**
 * Created by louiswang on 17/8/28.
 */
public enum ShardingDataSourceType {

    USERINFO("userInfo");

    private String name;

    private ShardingDataSourceType(String name) {

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
