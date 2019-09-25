package com.zf.website.controller;

import com.zf.website.ResponseResult;
import com.zf.website.bean.Banner;
import com.zf.website.bean.Logo;
import com.zf.website.config.FileUploadConfig;
import com.zf.website.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Model:首页管理前端控制器
 * Author:Tr9(韩峰)
 * Description: 包含logo设置 banner设置等
 * Time: 2019/9/23 13:41
 */
@RestController
public class IndexController {

    @Autowired
    private IIndexService indexService;

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @PostMapping("uploadLogo")
    public ResponseResult uploadLogo(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            String realPath = fileUploadConfig.getLogoRealPath() + "" + fileName;
            String relativePath = fileUploadConfig.getLogoRelativePath() + "" + fileName;
            File filePath = new File(fileUploadConfig.getLogoRealPath());
            if (!filePath.exists())
                filePath.mkdirs();
            file.transferTo(new File(realPath));
            Logo logo = new Logo(null, realPath, relativePath, false);
            Boolean flag = indexService.saveLogo(logo);
            if (flag)
                return ResponseResult.builder().code(200).message("上传成功").data(Boolean.TRUE).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(200).message("上传失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteLogo")
    public ResponseResult deleteLogo(Integer id) {
        boolean flag = indexService.deleteLogo(id);
        if (flag)
            return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @GetMapping("listLogo")
    public ResponseResult listLogo() {
        List<Logo> list = indexService.listLogo();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getUsedLogo")
    public ResponseResult getUsedLogo() {
        Logo logo = indexService.getUsedLogo();
        if (logo != null)
            return ResponseResult.builder().code(200).message("查询成功").data(logo).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @PutMapping("usedLogo")
    public ResponseResult usedLogo(Integer id) {
        boolean flag = indexService.usedLogo(id);
        if (flag)
            return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @PostMapping("saveBanner")
    public ResponseResult saveBanner(Banner banner,MultipartFile file){
        try {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            String realPath = fileUploadConfig.getBannerRealPath() + "" + fileName;
            String relativePath = fileUploadConfig.getBannerRelativePath() + "" + fileName;
            File filePath = new File(fileUploadConfig.getBannerRealPath());
            if (!filePath.exists())
                filePath.mkdirs();
            file.transferTo(new File(realPath));
            banner.setUsed(false);
            banner.setRealPath(realPath);
            banner.setRelativePath(relativePath);
            boolean flag = indexService.saveBanner(banner);
            if(flag)
                return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteBanner")
    public ResponseResult deleteBanner(Integer id){
        boolean flag = indexService.deleteBanner(id);
        if(flag)
            return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateBanner")
    public ResponseResult updateBanner(Integer editId,String editTitle,String editDescribe){
        Banner banner = new Banner();
        banner.setId(editId);
        banner.setTitle(editTitle);
        banner.setDescribe(editDescribe);
        boolean flag = indexService.updateBanner(banner);
        if(flag)
            return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @PutMapping("usedBanner")
    public ResponseResult usedBanner(Integer id){
        boolean flag = indexService.usedBanner(id);
        if(flag)
            return ResponseResult.builder().code(200).message("启用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("启用失败").data(Boolean.FALSE).build();
    }

    @PutMapping("unsedBanner")
    public ResponseResult unsedBanner(Integer id){
        boolean flag = indexService.unsedBanner(id);
        if(flag)
            return ResponseResult.builder().code(200).message("停用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("停用失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getBanner")
    public ResponseResult getBanner(Integer id){
        Banner banner = indexService.getBanner(id);
        if(banner != null)
            return ResponseResult.builder().code(200).message("查询成功").data(banner).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getUsedListBanner")
    public ResponseResult getUsedListBanner(){
        List<Banner> list = indexService.getUsedListBanner();
        if(list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("listBanner")
    public ResponseResult listBanner(){
        List<Banner> list = indexService.listBanner();
        if(list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

}
