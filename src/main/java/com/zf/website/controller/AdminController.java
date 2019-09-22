package com.zf.website.controller;

import com.zf.website.ResponseResult;
import com.zf.website.bean.User;
import com.zf.website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Model:后台管理模块
 * Author:Tr9(韩峰)
 * Description:用于后台管理的页面跳转
 * Time: 2019/9/19 17:56
 */
@Controller
public class AdminController {

    @Autowired
    private IUserService userService;

    @RequestMapping("admin")
    public String admin() {
        return "admin/adminLogin";
    }

    @RequestMapping("admin/main")
    public String main(){
        return "admin/adminMain";
    }

    @RequestMapping("admin/password/changePassword")
    public String changePassword(){
        return "admin/password/changePassword";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@Valid User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.builder().code(200).message("用户名或密码错误").data(Boolean.FALSE).build();
        } else {
            User loginUser = userService.login(user);
            if (loginUser != null) {
                session.setAttribute("loginUser", loginUser);
                return ResponseResult.builder().code(200).message("登陆成功").data(Boolean.TRUE).build();
            } else {
                return ResponseResult.builder().code(200).message("用户名或密码错误").data(Boolean.FALSE).build();
            }
        }
    }
}
