package cn.wantedonline.usercenter.domain;

/**
 * Created by wangcheng on 20/08/2017.
 * UserInfo表基本对应
 */
public class UserInfoPo extends BasePo {
    private long uid;
    private String userName;
    private String name;
    private int gender;
    private String password;
    private String salt;
    private String tel;
    private String email;
    private int lFrom; // 注册来源

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
}
