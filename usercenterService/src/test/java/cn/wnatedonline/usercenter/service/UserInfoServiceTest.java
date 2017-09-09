package cn.wnatedonline.usercenter.service;

import cn.wantedonline.usercenter.domain.UserBaseInfo;
import cn.wantedonline.usercenter.service.UserInfoService;
import cn.wnatedonline.usercenter.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by louiswang on 17/8/30.
 */
public class UserInfoServiceTest extends BaseTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testCreateNewUser() {
        UserBaseInfo baseInfo = new UserBaseInfo();
        baseInfo.setEmail("test@jumei.com");
        baseInfo.setGender(1);
        baseInfo.setlFrom(1);
        baseInfo.setName("test");
        baseInfo.setPassword("hellowthissipassworfsd");
        baseInfo.setUserName("testhaha");
        baseInfo.setTel("12241");
        userInfoService.createNewUser(baseInfo);
    }
}
