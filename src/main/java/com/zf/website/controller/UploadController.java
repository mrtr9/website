package com.zf.website.controller;

import com.zf.website.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Model:文件上传控制器
 * Author:Tr9(韩峰)
 * Description: 包含文件上传操作
 * Time: 2019/9/23 13:41
 */
@RestController
public class UploadController {

    @PostMapping("upload")
    public ResponseResult upload(MultipartFile multipartFile){

        return null;
    }
}
