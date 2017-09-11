package cn.wantedonline.usercenter.config;

import cn.wantedonline.usercenter.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by louiswang on 17/9/11.
 */
public class RedisTest extends BaseTest {

    @Autowired
    private JedisHelper jedisHelper;

    @Test
    public void testJedis() {
        jedisHelper.set("test", "wangcheng");
        Assert.assertEquals("wangcheng", jedisHelper.get("test"));
    }
}
