package cn.wantedonline.usercenter.domain;

/**
 * Created by wangcheng on 20/08/2017.
 * PO类公共字段 各个PO类继承此类
 */
public class BasePo {
    private long id; //数据库主键 自增长id
    private long createTime; // 记录创建时间 unix时间戳 精确到毫秒 13位
    private long updateTime; // 记录最后一次更新时间 unix时间戳 精确到毫秒 13位

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
