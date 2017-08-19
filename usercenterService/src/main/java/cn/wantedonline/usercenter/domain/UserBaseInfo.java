package cn.wantedonline.usercenter.domain;

import java.util.Date;

/**
 * Created by wangcheng on 05/08/2017.
 * 用户注册基本信息实体 对应数据库userserinfo表
 */
public class UserBaseInfo {
    private long id;
    private String userName;
    private short gender;
    private String password;
    private String tel;
    private String email;
    private short lFrom; // 注册来源
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getlFrom() {
        return lFrom;
    }

    public void setlFrom(short lFrom) {
        this.lFrom = lFrom;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", lFrom=" + lFrom +
                ", updateTime=" + updateTime +
                '}';
    }
}
