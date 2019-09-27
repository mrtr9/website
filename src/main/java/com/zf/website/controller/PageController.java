package com.zf.website.controller;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Footer;
import com.zf.website.bean.Link;
import com.zf.website.bean.Logo;
import com.zf.website.service.IFooterService;
import com.zf.website.service.IIndexService;
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

    @RequestMapping("index.html")
    public String index(Model model){
        Logo logo = indexService.getUsedLogo();
        List<Banner> bannerList = indexService.getUsedListBanner();
        List<Link> linkList = footerService.listLinks();
        Footer footer = footerService.getFooter();
        model.addAttribute("logo", logo);
        model.addAttribute("bannerList", bannerList);
        model.addAttribute("linkList", linkList);
        model.addAttribute("footer", footer);
        return "index";
    }

    @RequestMapping("about.html")
    public String about(){
        return "about";
    }

    @RequestMapping("case.html")
    public String caseHtml(){
        return "case";
    }

    @RequestMapping("news.html")
    public String news(){
        return "news";
    }

    @RequestMapping("newsDetail.html")
    public String newsDetail(){
        return "newsDetail";
    }

    @RequestMapping("product.html")
    public String product(){
        return "product";
    }

}
