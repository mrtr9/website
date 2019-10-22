package com.zf.website.controller;

import com.zf.website.ResponseResult;
import com.zf.website.bean.dto.ImageUploadDTO;
import com.zf.website.config.FileUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/8 10:57
 */
@RestController
@RequestMapping("common")
public class FileUploadController {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @RequestMapping("upload")
    public ResponseResult upload(String editorid, MultipartFile file) {
        if (file != null) {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            String path = "";
            String realPath = "";
            String relativePath = "";
            if (editorid.equals("companyProfileEditor")) {
                path = fileUploadConfig.getAboutRealPath();
                realPath = fileUploadConfig.getAboutRealPath() + "" + fileName;
                relativePath = fileUploadConfig.getAboutRelativePath() + "" + fileName;
            }
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            try {
                file.transferTo(new File(realPath));
                ImageUploadDTO imageUploadDTO = ImageUploadDTO.builder().title(file.getOriginalFilename()).src(relativePath).realPath(realPath).build();
                return ResponseResult.builder().code(200).message("SUCCESS").data(imageUploadDTO).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseResult.builder().code(200).message("UNKNOWN").data(Boolean.FALSE).build();
    }
}
