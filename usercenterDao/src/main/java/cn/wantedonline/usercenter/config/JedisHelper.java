package cn.wantedonline.usercenter.config;

import redis.clients.jedis.Jedis;

/**
 * Created by louiswang on 17/9/11.
 */
public class JedisHelper extends Jedis {
    private String ip;
    private int port;
    private String userName;
    private String pwd;

    public JedisHelper(String ip, int port) {
        super(ip, port);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
