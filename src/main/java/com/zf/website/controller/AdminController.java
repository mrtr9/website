package com.zf.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Model:后台管理模块
 * Author:Tr9(韩峰)
 * Description:用于后台管理的页面跳转
 * Time: 2019/9/19 17:56
 */
@Controller
public class AdminController {

    @RequestMapping("admin")
    public String admin(){
        return "admin/adminLogin";
    }
}
