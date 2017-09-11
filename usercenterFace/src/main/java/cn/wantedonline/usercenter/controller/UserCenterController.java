package cn.wantedonline.usercenter.controller;

import cn.wantedonline.common.base.BaseRtnObject;
import cn.wantedonline.common.base.SystemConstant;
import cn.wantedonline.common.utils.ParamsCheckUtils;
import cn.wantedonline.usercenter.service.UserInfoService;
import cn.wantedonline.usercenter.domain.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangcheng on 05/08/2017.
 */
@Controller
@RequestMapping("usercenter/")
public class UserCenterController {
    Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(UserBaseInfo userBaseInfo) throws Exception {

        logger.info("[usercenter/register], params is {}", userBaseInfo);

        //===start parameter check
        Assert.hasLength(userBaseInfo.getUserName(), "UserName不能为空");

        ParamsCheckUtils.intValueIn("Gender取值错误", userBaseInfo.getGender(),
                SystemConstant.CONS_GENDER_MALE, SystemConstant.CONS_GENDER_FEMAILE);

        Assert.hasLength(userBaseInfo.getPassword(), "Password不能为空");

        Assert.hasLength(userBaseInfo.getTel(), "tel不能为空");
        //===end parameter check

        long userId = userInfoService.createNewUser(userBaseInfo);

        return BaseRtnObject.createRtnObject(SystemConstant.HTTP_OK, "OK", userId);
    }

    /**
     * 登录支持
     * @param username username和
     * @param tel
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(String username, String tel, String pwd) {

        return null;
    }
}
