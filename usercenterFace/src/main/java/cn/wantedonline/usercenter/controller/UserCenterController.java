package cn.wantedonline.usercenter.controller;

import cn.wantedonline.common.base.BaseRtnObject;
import cn.wantedonline.common.base.SystemConstant;
import cn.wantedonline.common.utils.CheckUtil;
import cn.wantedonline.usercenter.service.UserInfoService;
import cn.wantedonline.usercenter.domain.UserBaseInfo;
import cn.wantedonline.usercenter.exception.InvalidParametersException;
import cn.wantedonline.usercenter.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        if (CheckUtil.isEmptyStr(userBaseInfo.getUserName())) {
            throw new InvalidParametersException("UserName不能为空");
        }

        if (CheckUtil.shortValueNotIn(userBaseInfo.getGender(), (short)1 ,(short)2)) {
            throw new InvalidParametersException("Gender取值错误");
        }

        if (CheckUtil.isEmptyStr(userBaseInfo.getPassword())) {
            throw new InvalidParametersException("Password不能为空");
        }

        if (CheckUtil.isEmptyStr(userBaseInfo.getTel())) {
            throw new InvalidParametersException("tel不能为空");
        }
        //===end parameter check

        long userId = userInfoService.createNewUser(userBaseInfo);
        if (CheckUtil.longEqual(userId, (long)-1)) {
            throw new ServiceException("服务器异常，请稍后再试");
        }

        return BaseRtnObject.createRtnObject(SystemConstant.HTTP_OK, "OK", userId);
    }
}
