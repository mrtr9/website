package com.zf.website.interceptor;

import com.zf.website.bean.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/7/24 10:14
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user != null) {
            String redirect = request.getParameter("redirect");
            if(redirect != null && !StringUtils.isEmpty(redirect)){
                response.sendRedirect(redirect);
                return false;
            }
            return true;
        } else {
            String url = request.getRequestURL().toString();
            request.setAttribute("redirect",url );
            request.getRequestDispatcher("/admin").forward(request, response);
            return false;
        }
    }
}