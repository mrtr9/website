package com.zf.website.controller;

import com.zf.website.ResponseResult;
import com.zf.website.bean.Footer;
import com.zf.website.bean.Link;
import com.zf.website.bean.LinkForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/26 14:23
 */
@RestController
public class FooterController {

    @PostMapping("saveFooter")
    public ResponseResult saveFooter(Footer footer, MultipartFile file) {

        return null;
    }

    @PostMapping("saveLinks")
    public ResponseResult saveLink(LinkForm linkForm){

        return null;
    }
}
