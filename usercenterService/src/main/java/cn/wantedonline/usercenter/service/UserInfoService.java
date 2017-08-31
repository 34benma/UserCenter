package cn.wantedonline.usercenter.service;

import cn.wantedonline.usercenter.dao.UserInfoMapper;
import cn.wantedonline.usercenter.domain.UserBaseInfo;
import cn.wantedonline.usercenter.domain.UserInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangcheng on 20/08/2017.
 */
@Service
public class UserInfoService {
    @Autowired
    private UserIdGenerator userIdGenerator;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 创建新用户 返回UserId
     * @param userBaseInfo
     * @return
     */
    public long createNewUser(UserBaseInfo userBaseInfo) {
        UserInfoPo po = packUserBaseInfo(userBaseInfo);
        userInfoMapper.insertNewUser(po);
        return -1;
    }

    private UserInfoPo packUserBaseInfo(UserBaseInfo userBaseInfo) {
        UserInfoPo po = new UserInfoPo();
        po.setEmail(userBaseInfo.getEmail());
        po.setGender(userBaseInfo.getGender());
        po.setlFrom(userBaseInfo.getlFrom());
        po.setName(userBaseInfo.getName());
        po.setPassword(userBaseInfo.getPassword());
        po.setSalt("111");
        po.setTel(userBaseInfo.getTel());
        po.setUserName(userBaseInfo.getUserName());
        po.setCreateTime(System.currentTimeMillis()/1000);
        po.setUpdateTime(po.getCreateTime());
        po.setUid(userIdGenerator.nextId());
        return po;
    }
}
