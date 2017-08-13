package cn.wantedonline.usercenter.controller;

import cn.wantedonline.common.base.BaseRtnObject;
import cn.wantedonline.common.base.RtnCode;
import cn.wantedonline.usercenter.domain.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangcheng on 05/08/2017.
 */
@Controller
@RequestMapping("usercenter/")
public class UserCenterController {
    Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(UserBaseInfo userBaseInfo) {
        logger.info("[usercenter/register], params is {}", userBaseInfo);
        return BaseRtnObject.createRtnObject(RtnCode.HTTP_OK, "OK");
    }
}
