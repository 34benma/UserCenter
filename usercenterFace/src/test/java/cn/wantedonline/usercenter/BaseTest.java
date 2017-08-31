package cn.wantedonline.usercenter;

import cn.wantedonline.usercenter.config.DaoRootConfig;
import cn.wantedonline.usercenter.config.FaceRootConfig;
import cn.wantedonline.usercenter.config.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by wangcheng on 12/08/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FaceRootConfig.class, WebConfig.class})
@WebAppConfiguration
public class BaseTest {
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNothing() {
        Assert.assertTrue(true);
    }
}
