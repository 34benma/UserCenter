package cn.wantedonline.usercenter.domain;

/**
 * Created by wangcheng on 05/08/2017.
 * 用户注册基本信息实体 对应数据库userserinfo表
 */
public class UserBaseInfo {
    private String name;
    private String userName;
    private int gender;
    private String password;
    private String tel;
    private String email;
    private int lFrom; // 注册来源

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public int getlFrom() {
        return lFrom;
    }

    public void setlFrom(int lFrom) {
        this.lFrom = lFrom;
    }

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", lFrom=" + lFrom +
                '}';
    }
}
