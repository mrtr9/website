package com.zf.website.config;

import com.zf.website.interceptor.LoginInterceptor;
import com.zf.website.interceptor.PjaxInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/19 16:01
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUploadConfig.getRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getRealPath());
        registry.addResourceHandler(fileUploadConfig.getLogoRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getLogoRealPath());
        registry.addResourceHandler(fileUploadConfig.getBannerRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getBannerRealPath());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<String>();
        excludePathList.add("/admin");
        excludePathList.add("/about.html");
        excludePathList.add("/case.html");
        excludePathList.add("/index.html");
        excludePathList.add("/news.html");
        excludePathList.add("/newsDetail.html");
        excludePathList.add("/product.html");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns(excludePathList);
//        registry.addInterceptor(new PjaxInterceptor());
    }
}
