package com.zrtg.authtest.controller;

import com.zrtg.auth.annotation.Auth;
import com.zrtg.auth.constant.PermissionTypeConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Auth(id = 100, value = "控制器模块", code = "Controller-manage", type = PermissionTypeConstant.PAGE)
@RestController
public class MyController {

    @Auth(id = 1, value = "测试模块", code = "test")
    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "test";
    }

}
