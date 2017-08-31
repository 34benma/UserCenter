package cn.wantedonline.common.utils;

import cn.wantedonline.common.BaseTest;
import org.junit.Test;

/**
 * Created by louiswang on 17/8/31.
 */
public class CheckUtilsTest extends BaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIntValueInCase1() {
        ParamsCheckUtils.intValueIn("can't find", 1, 2, 3, 4);
        thrown.expectMessage("can't find");
    }

    @Test
    public void testIntValueInCase2() {
        ParamsCheckUtils.intValueIn("can't find", 1, 1, 2, 3, 4);
    }
}
