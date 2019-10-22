package com.zf.website.config;

import com.zf.website.converter.StringToLocalDateConverter;
import com.zf.website.interceptor.LoginInterceptor;
import com.zf.website.interceptor.PjaxInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
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

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUploadConfig.getRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getRealPath());
        registry.addResourceHandler(fileUploadConfig.getLogoRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getLogoRealPath());
        registry.addResourceHandler(fileUploadConfig.getBannerRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getBannerRealPath());
        registry.addResourceHandler(fileUploadConfig.getProductRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getProductRealPath());
        registry.addResourceHandler(fileUploadConfig.getAboutRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getAboutRealPath());
        registry.addResourceHandler(fileUploadConfig.getNewsRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getNewsRealPath());
        registry.addResourceHandler(fileUploadConfig.getCaseRelativePath() + "**").addResourceLocations("file:" + fileUploadConfig.getCaseRealPath());
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
    }

    @PostConstruct
    public void initEditableAvlidation() {

        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToLocalDateConverter());//添加自定义的类型转换器
        }
    }
}
