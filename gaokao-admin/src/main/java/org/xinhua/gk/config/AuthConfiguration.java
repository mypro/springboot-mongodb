package org.xinhua.gk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xinhua.authority.web.aop.AuthorizationAttributeSourceAdvisor;
import org.xinhua.authority.web.service.AuthAPIService;
import org.xinhua.authority.web.utils.SpringContextUtil;

/**
 * @Classname AuthConfiguration
 * @Description TODO
 * @Date 2021/4/30 9:48
 * @Created by Chen Weichao
 */

@Configuration
public class AuthConfiguration {

    @Autowired
    private AuthAPIService authAPIService;

    //开启对权限注解的支持
    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }

    //开启对权限注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(AuthAPIService authAPIService) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        return advisor;
    }


}
