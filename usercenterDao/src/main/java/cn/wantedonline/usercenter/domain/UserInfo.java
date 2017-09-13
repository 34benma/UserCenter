package cn.wantedonline.usercenter.domain;

/**
 * Created by louiswang on 17/9/11.
 */
public class UserInfo extends UserInfoPo {
    private String sessionId;
    private long loginTime;

    public UserInfo() {

    }

    public UserInfo(String sessionId, UserInfoPo userInfoPo, long loginTime) {
        this.sessionId = sessionId;
        this.loginTime = loginTime;
        this.setUid(userInfoPo.getUid());
        this.setUserName(userInfoPo.getUserName());
        this.setEmail(userInfoPo.getEmail());
        this.setGender(userInfoPo.getGender());
        this.setlFrom(userInfoPo.getlFrom());
        this.setTel(userInfoPo.getTel());
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }
}
