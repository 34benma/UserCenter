package cn.wantedonline.usercenter.jedis;

/**
 * Created by louiswang on 17/9/13.
 */
public class RedisConstants {
    // session 缓存过期时间
    public static final int SESSION_EXPIRE_SECONDS = 7*24*60*60;

    public static String getSessionKey(final long uid, final String userName) {
        return "session_" + uid + "_" + userName;
    }

    private RedisConstants() {}
}
