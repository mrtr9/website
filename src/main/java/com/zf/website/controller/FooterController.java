package com.zf.website.controller;

import com.zf.website.ResponseResult;
import com.zf.website.bean.Footer;
import com.zf.website.bean.Link;
import com.zf.website.bean.dto.LinkFormDTO;
import com.zf.website.service.IFooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/26 14:23
 */
@RestController
public class FooterController {

    @Autowired
    private IFooterService footerService;

    @PostMapping("saveFooter")
    public ResponseResult saveFooter(Footer footer, MultipartFile file) throws IOException {
        boolean flag = footerService.editFooter(footer,file);
        if(flag){
            return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getFooter")
    public ResponseResult getFooter(){
        Footer footer = footerService.getFooter();
        if(footer != null)
            return ResponseResult.builder().code(200).message("查询成功").data(footer).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @PostMapping("saveLinks")
    public ResponseResult saveLink(LinkFormDTO linkForm) {
        boolean flag = footerService.saveLinks(linkForm.getLinks());
        if (flag)
            return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @GetMapping("listLinks")
    public ResponseResult listLinks() {
        List<Link> list = footerService.listLinks();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }
}
