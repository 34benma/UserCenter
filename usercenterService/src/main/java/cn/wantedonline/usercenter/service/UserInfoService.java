package cn.wantedonline.usercenter.service;

import cn.wantedonline.common.security.EncryptUtils;
import cn.wantedonline.common.utils.SnowflakeIDWorker;
import cn.wantedonline.usercenter.dao.UserInfoCache;
import cn.wantedonline.usercenter.dao.UserInfoMapper;
import cn.wantedonline.usercenter.domain.UserBaseInfo;
import cn.wantedonline.usercenter.domain.UserInfo;
import cn.wantedonline.usercenter.domain.UserInfoPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangcheng on 20/08/2017.
 */
@Service
public class UserInfoService {
    private Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserIdGenerator userIdGenerator;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoCache userInfoCache;

    @Autowired
    private SnowflakeIDWorker snowflakeIDWorker;

    private UserInfoPo packUserBaseInfo(UserBaseInfo userBaseInfo) throws UnsupportedEncodingException {
        String salt = EncryptUtils.getRandomLong() + "";
        String encrypedPwd = EncryptUtils.encoderByMD5(userBaseInfo.getPassword()+salt);
        UserInfoPo po = new UserInfoPo();
        po.setEmail(userBaseInfo.getEmail());
        po.setGender(userBaseInfo.getGender());
        po.setlFrom(userBaseInfo.getlFrom());
        po.setName(userBaseInfo.getName());
        po.setPassword(encrypedPwd);
        po.setSalt(salt);
        po.setTel(userBaseInfo.getTel());
        po.setUserName(userBaseInfo.getUserName());
        po.setCreateTime(System.currentTimeMillis()/1000);
        po.setUpdateTime(po.getCreateTime());
        po.setUid(userIdGenerator.nextId());
        return po;
    }

    private UserInfo createUserSession(UserInfoPo userInfoPo) {
        long loginTime = System.currentTimeMillis();
        String sessionId = "sid_" + snowflakeIDWorker.nextId();
        UserInfo userInfo = new UserInfo(sessionId, userInfoPo, loginTime);
        return userInfo;
    }

    /**
     * 创建新用户 返回UserId
     * @param userBaseInfo
     * @return
     */
    public long createNewUser(UserBaseInfo userBaseInfo) {
        try {
            //TODO: userName tel 重复性校验 单独成接口？
            UserInfoPo po = packUserBaseInfo(userBaseInfo);
            userInfoMapper.insertNewUser(po);
            //插入session缓存 注册成功即可登录
            userInfoCache.insertUserInfo(createUserSession(po));
            return po.getUid();
        } catch (UnsupportedEncodingException e) {
            logger.error("unsupported md5, can't encryp password... error info is {}", e.getMessage());
        }
        return -1;
    }

    /**
     * 用户登录 返回登录态
     * @param username
     * @param tel
     * @param pwd
     * @return
     */
    public UserInfo login(String username, String tel, String pwd) {

        return null;
    }
}
