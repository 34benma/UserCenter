package cn.wantedonline.usercenter.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by wangcheng on 05/08/2017.
 */
public class UserCenterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
