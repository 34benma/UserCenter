package cn.wantedonline.usercenter.controller;

import cn.wantedonline.common.base.BaseRtnObject;
import cn.wantedonline.common.base.SystemConstant;
import cn.wantedonline.usercenter.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangcheng on 19/08/2017.
 * Api异常处理器
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public BaseRtnObject handleIllegalArgumentException(IllegalArgumentException e) {

        return BaseRtnObject.createRtnObject(SystemConstant.HTTP_INVALID_PARAMETER, e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public BaseRtnObject handleServiceException(ServiceException e) {
        return BaseRtnObject.createRtnObject(SystemConstant.HTTP_SERVER_EXCEPTION, e.getMessage());
    }
}
