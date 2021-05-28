package com.zrtg.auth.annotation;

import com.zrtg.auth.constant.PermissionTypeConstant;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    /**
     * 手动主键，权限ID
     */
    int id();

    /**
     * 权限名
     */
    String value() default "";

    /**
     * 权限代码
     */
    String code() default "";

    /**
     *  权限类型默认操作权限类型
     */
    int type() default PermissionTypeConstant.OPERATE;

}
