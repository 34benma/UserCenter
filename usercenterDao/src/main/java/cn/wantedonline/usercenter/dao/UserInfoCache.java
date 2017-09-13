package cn.wantedonline.usercenter.dao;

import cn.wantedonline.common.utils.ParamsCheckUtils;
import cn.wantedonline.usercenter.domain.UserInfo;
import cn.wantedonline.usercenter.jedis.JedisTemplate;
import cn.wantedonline.usercenter.jedis.RedisConstants;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by louiswang on 17/9/13.
 */
@Repository
public class UserInfoCache {

    @Autowired
    private JedisTemplate jedisTemplate;

    public UserInfo insertUserInfo(final UserInfo userInfo) {
        String key = RedisConstants.getSessionKey(userInfo.getUid(), userInfo.getUserName());
        jedisTemplate.set(key, JSON.toJSONString(userInfo));
        jedisTemplate.expire(key, RedisConstants.SESSION_EXPIRE_SECONDS);
        return userInfo;
    }

    public UserInfo getUserInfo(final long uid, final String userName) {
        String key = RedisConstants.getSessionKey(uid, userName);
        String userInfoStr = jedisTemplate.get(key);

        if (ParamsCheckUtils.isNotNull(userInfoStr)) {
            return JSON.parseObject(userInfoStr, UserInfo.class);
        }

        return null;
    }
}
