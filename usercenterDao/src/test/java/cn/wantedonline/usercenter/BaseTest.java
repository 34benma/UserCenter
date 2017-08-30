package cn.wantedonline.usercenter;

import cn.wantedonline.usercenter.config.DaoRootConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by louiswang on 17/8/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoRootConfig.class})
public class BaseTest {
    @Test
    public void testNothing() {
        Assert.assertTrue(true);
    }
}
