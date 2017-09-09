package cn.wantedonline.common.utils;

import cn.wantedonline.common.BaseTest;
import cn.wantedonline.common.security.EncryptUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by louiswang on 17/9/8.
 */
public class EncryptUtilsTest extends BaseTest {

    @Test
    public void testEncoderByMD5() throws UnsupportedEncodingException {
        Assert.assertEquals("DIjVZpTC+zvMQW4SLBBy6w==", EncryptUtils.encoderByMD5("4QrcOUm6Wau+VuBX8g+IPg=="));
        Assert.assertEquals("dz0oxwzpEXjDlg1PHKq23g==", EncryptUtils.encoderByMD5("fasdf4234efdsfer4t34rf34faerferf4QrcOUm6Wau+VuBX8g+IPg=="));
    }

    @Test
    public void testGetRandomLong() {
        System.out.println(EncryptUtils.getRandomLong());
        System.out.println(EncryptUtils.getRandomLong());
        System.out.println(EncryptUtils.getRandomLong());
        System.out.println(EncryptUtils.getRandomLong());
    }
}
