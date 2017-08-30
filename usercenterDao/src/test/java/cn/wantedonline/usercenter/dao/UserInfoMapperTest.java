package cn.wantedonline.usercenter.dao;

import cn.wantedonline.usercenter.BaseTest;
import cn.wantedonline.usercenter.domain.UserInfoPo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by louiswang on 17/8/30.
 */
public class UserInfoMapperTest extends BaseTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testInsertNewUser() {
        UserInfoPo po = new UserInfoPo();
        po.setUid(10000L);
        po.setUpdateTime(System.currentTimeMillis()/1000);
        po.setCreateTime(System.currentTimeMillis()/1000);
        po.setUserName("tester");
        po.setTel("12345");
        po.setSalt("11212");
        po.setPassword("abagfdsff");
        po.setName("hello");
        po.setlFrom((short)1);
        po.setGender((short)1);
        po.setEmail("test@jumei.com");
        int rtn = userInfoMapper.insertNewUser(po);
        System.out.println(rtn);
    }

}
