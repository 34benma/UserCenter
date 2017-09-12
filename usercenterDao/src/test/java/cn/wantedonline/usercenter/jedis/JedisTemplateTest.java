package cn.wantedonline.usercenter.jedis;

import cn.wantedonline.usercenter.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by louiswang on 17/9/12.
 */
public class JedisTemplateTest extends BaseTest {

    @Autowired
    private JedisTemplate template;

    @Test
    public void testInfo() {
        String info = template.info();
        System.out.println(info);
        String cpuInfo = template.info("cpu");
        System.out.println(cpuInfo);
    }

}
