package com.zrtg.auth.core;

import cn.hutool.core.bean.BeanUtil;
import com.zrtg.auth.annotation.Auth;
import com.zrtg.auth.constant.PermissionTypeConstant;
import com.zrtg.auth.properties.AuthConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

public class AuthHandler implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    private List<Permission> pList = new ArrayList<>();

    private AuthConfigurationProperties authConfigurationProperties;

    public AuthHandler(AuthConfigurationProperties authConfigurationProperties) {
        this.authConfigurationProperties = authConfigurationProperties;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        handleAuthAnnotation();
        saveToDatabase();
        logger.info(">>>>>>>>>> 权限初始化完成");
    }


    private void saveToDatabase() {
        for (Permission permission : pList) {
            Object[] objects = BeanUtil.beanToMap(permission).values().toArray();
            try {
                jdbcTemplate.queryForObject(SqlHandler.DEFAULT_SELECT_ID_STATEMENT, Integer.class, objects[0]);
            } catch (EmptyResultDataAccessException e) {
                jdbcTemplate.update(SqlHandler.DEFAULT_INSERT_STATEMENT, objects);
                logger.info("ID {} 添加成功！", objects[0]);
            }
        }
    }


    private void handleAuthAnnotation() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((info, handlerMethod) -> {
            // 拿到类(模块)上的权限注解
            Auth classAuth = handlerMethod.getBeanType().getAnnotation(Auth.class);
            if (classAuth != null) {
                // 存储类信息
                saveClassAuth(classAuth);
                // 拿到接口方法上的权限注解
                Auth methodAuth = handlerMethod.getMethod().getAnnotation(Auth.class);
                if (methodAuth != null) {
                    // 存储方法信息
                    saveMethodAuth(classAuth, methodAuth, info);
                }
            }
        });
    }

    private void saveClassAuth(Auth classAuth) {
        Permission permission = new Permission();
        permission.setId(classAuth.id());
        permission.setParentId(0);
        permission.setPermissionName(classAuth.value());
        permission.setPermissionCode(classAuth.code());
        permission.setPermissionType(PermissionTypeConstant.PAGE);
        pList.add(permission);
    }

    private void saveMethodAuth(Auth classAuth, Auth methodAuth, RequestMappingInfo info) {
        Permission permission = new Permission();
        permission.setId(methodAuth.id() + classAuth.id());
        permission.setParentId(classAuth.id());
        permission.setPermissionName(methodAuth.value());
        permission.setPermissionCode(methodAuth.code());
        Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
        if (methods.size() != 1) {
            return;
        }
        permission.setPermissionMethod(methods.toArray()[0].toString());
        String path = info.getPatternsCondition().getPatterns().toArray()[0].toString();
        permission.setPermissionPath(authConfigurationProperties.getConfig().getApp() + path);
        permission.setPermissionType(PermissionTypeConstant.OPERATE);
        pList.add(permission);
    }




}
