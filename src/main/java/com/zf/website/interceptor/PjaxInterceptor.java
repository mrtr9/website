package com.zf.website.interceptor;

import com.zf.website.controller.PageController;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Model:Pjax拦截器
 * Author:Tr9(韩峰)
 * Description:用于拦截Pjax请求
 * Time: 2019/7/18 13:48
 */
public class PjaxInterceptor implements HandlerInterceptor {

    private static final String DESC = "desc";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accept = request.getHeader("Accept");
        if (accept.contains("json"))
            return true;
        String path = request.getServletPath();
        if (path.lastIndexOf(".html") == -1)
            return true;
        String header = request.getHeader("X-PJAX");
        String desc = request.getParameter(DESC);
        if (header == null && !path.contains(PageController.INDEX) && desc == null) {
            Enumeration<String> names = request.getParameterNames();
            StringBuilder sb = new StringBuilder();
            while (names.hasMoreElements()) {
                String s = names.nextElement();
                String param = request.getParameter(s);
                if (param == null || param.length() < 1)
                    continue;
                sb.append(s).append("=").append(param).append("&");
            }
            String params = sb.length() > 1 ? sb.substring(0, sb.length() - 1) : "";
            request.setAttribute("params", params);
            request.setAttribute("path", path);
            String url = PageController.INDEX;
            request.getRequestDispatcher(url).forward(request, response);
            return false;
        }
        return true;
    }
}
