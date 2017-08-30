package cn.wantedonline.usercenter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by wangcheng on 05/08/2017.
 */
@Configuration
@ComponentScan(basePackages = {"cn.wantedonline"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class FaceRootConfig extends ServiceRootConfig {
}
