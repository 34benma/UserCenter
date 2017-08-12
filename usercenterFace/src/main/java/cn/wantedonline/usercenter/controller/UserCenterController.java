package cn.wantedonline.usercenter.controller;

import cn.wantedonline.common.base.BaseRtnObject;
import cn.wantedonline.common.base.RtnCode;
import cn.wantedonline.usercenter.domain.UserBaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangcheng on 05/08/2017.
 */
@Controller
@RequestMapping("usercenter/")
public class UserCenterController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(UserBaseInfo userBaseInfo) {
        return BaseRtnObject.createRtnObject(RtnCode.HTTP_OK, "OK");
    }
}
