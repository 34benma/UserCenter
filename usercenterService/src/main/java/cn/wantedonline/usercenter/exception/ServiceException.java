package cn.wantedonline.usercenter.exception;

/**
 * Created by wangcheng on 20/08/2017.
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}