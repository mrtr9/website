package com.zf.website.controller;

import com.zf.website.bean.*;
import com.zf.website.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Model:页面Controller
 * Author:Tr9(韩峰)
 * Description: 用于填充页面数据并跳转
 * Time: 2019/9/19 15:59
 */
@Controller
public class PageController {

    public static final String INDEX = "index.html";

    @Autowired
    private IIndexService indexService;

    @Autowired
    private IFooterService footerService;

    @Autowired
    private IAboutService aboutService;

    @Autowired
    private IProductService productService;

    @Autowired
    private INameService nameService;

    @Autowired
    private INewsService newsService;

    @Autowired
    private ICaseService caseService;

    @RequestMapping("index.html")
    public String index(Model model){
        setPublicModel(model);
        List<Banner> bannerList = indexService.getUsedListBanner();
        model.addAttribute("bannerList", bannerList);
        return "index";
    }

    @RequestMapping("about.html")
    public String about(Model model){
        setPublicModel(model);
        Banner banner = aboutService.getUsedBanner();
        CompanyProfile companyProfile = aboutService.getCompanyProfile();
        List<Course> courseList = aboutService.listAllCourse();
        model.addAttribute("banner", banner);
        model.addAttribute("companyProfile", companyProfile);
        model.addAttribute("courseList", courseList);
        return "about";
    }

    @RequestMapping("case.html")
    public String caseHtml(Model model){
        setPublicModel(model);
        int countCase = caseService.countCase();
        Banner banner = caseService.getUsedBanner();
        model.addAttribute("countCase", countCase);
        model.addAttribute("banner", banner);
        return "case";
    }

    @RequestMapping("news.html")
    public String news(Model model){
        setPublicModel(model);
        int countNews = newsService.countNews();
        Banner banner = newsService.getUsedBanner();
        model.addAttribute("countNews", countNews);
        model.addAttribute("banner", banner);
        return "news";
    }

    @RequestMapping("newsDetail.html")
    public String newsDetail(Model model){
        setPublicModel(model);
        return "newsDetail";
    }

    @RequestMapping("product.html")
    public String product(Model model){
        setPublicModel(model);
        int countProduct = productService.countProduct();
        Banner banner = productService.getUsedBanner();
        model.addAttribute("countProduct", countProduct);
        model.addAttribute("banner", banner);
        return "product";
    }

    @RequestMapping("productDetail.html")
    public String productDetail(Model model){
        setPublicModel(model);
        return "productDetail";
    }

    private void setPublicModel(Model model){
        Logo logo = indexService.getUsedLogo();
        List<Link> linkList = footerService.listLinks();
        Footer footer = footerService.getFooter();
        Name name = nameService.getName();
        model.addAttribute("logo", logo);
        model.addAttribute("linkList", linkList);
        model.addAttribute("footer", footer);
        model.addAttribute("name", name);
    }

}
