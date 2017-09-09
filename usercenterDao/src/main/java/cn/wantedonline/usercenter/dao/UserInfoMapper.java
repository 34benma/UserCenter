package cn.wantedonline.usercenter.dao;

import cn.wantedonline.usercenter.domain.UserInfoPo;
import org.springframework.stereotype.Repository;

/**
 * Created by wangcheng on 26/08/2017.
 */
@Repository
public interface UserInfoMapper {
    int insertNewUser(UserInfoPo userInfoPo);
}
