package com.zrtg.auth.core;

import com.zrtg.auth.annotation.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;

public class AuthScanner implements BeanFactoryPostProcessor {

    private Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    public static Map<String, Object> AuthBeans = new HashMap<>();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AuthBeans = beanFactory.getBeansWithAnnotation(Auth.class);
    }

}
