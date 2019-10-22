package com.zf.website.controller;

import com.zf.website.ResponsePageResult;
import com.zf.website.ResponseResult;
import com.zf.website.bean.Banner;
import com.zf.website.bean.Case;
import com.zf.website.config.FileUploadConfig;
import com.zf.website.service.ICaseService;
import com.zf.website.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/22 10:24
 */
@RestController
@RequestMapping("case")
public class CaseController {

    @Autowired
    private ICaseService caseService;

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @PostMapping("saveBanner")
    public ResponseResult saveBanner(Banner banner, MultipartFile file) {
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
            boolean flag = caseService.saveBanner(banner);
            if (flag)
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("操作成功").data(Boolean.TRUE).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteBanner")
    public ResponseResult deleteBanner(Integer id) {
        boolean flag = caseService.deleteBanner(id);
        if (flag)
            return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateBanner")
    public ResponseResult updateBanner(Integer editId, String editTitle, String editDescribe) {
        Banner banner = new Banner();
        banner.setId(editId);
        banner.setTitle(editTitle);
        banner.setDescribe(editDescribe);
        boolean flag = caseService.updateBanner(banner);
        if (flag)
            return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @PutMapping("usedBanner")
    public ResponseResult usedBanner(Integer id) {
        boolean flag = caseService.usedBanner(id);
        if (flag)
            return ResponseResult.builder().code(200).message("启用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("启用失败").data(Boolean.FALSE).build();
    }

    @PutMapping("unsedBanner")
    public ResponseResult unsedBanner() {
        boolean flag = caseService.unsedBanner();
        if (flag)
            return ResponseResult.builder().code(200).message("停用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("停用失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getBanner")
    public ResponseResult getBanner(Integer id) {
        Banner banner = caseService.getBanner(id);
        if (banner != null)
            return ResponseResult.builder().code(200).message("查询成功").data(banner).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getUsedListBanner")
    public ResponseResult getUsedListBanner() {
        List<Banner> list = caseService.getUsedListBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("listBanner")
    public ResponseResult listBanner() {
        List<Banner> list = caseService.listBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @PostMapping("saveCase")
    public ResponseResult saveCase(Case c, MultipartFile file) throws IOException {
        if (!BeanUtil.isNull(c)) {
            if (file != null) {
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getCaseRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getCaseRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getCaseRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                file.transferTo(new File(realPath));
                c.setImgRealPath(realPath);
                c.setImgRelativePath(relativePath);
            }
            c.setDate(LocalDate.now());
            boolean flag = caseService.saveCase(c);
            if (flag)
                return ResponseResult.builder().code(200).message("保存成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("保存失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteCase")
    public ResponseResult deleteCase(Integer id) {
        if (id != null) {
            boolean flag = caseService.deleteCase(id);
            if (flag)
                return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateCase")
    public ResponseResult updateCase(Case c,MultipartFile file) throws IOException {
        if(!BeanUtil.isNull(c)){
            if (file != null) {
                String fileSourcePath = c.getImgRealPath();
                if (fileSourcePath != null) {
                    new File(fileSourcePath).delete();
                }
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getCaseRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getCaseRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getCaseRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                file.transferTo(new File(realPath));
                c.setImgRealPath(realPath);
                c.setImgRelativePath(relativePath);
            }
            c.setDate(LocalDate.now());
            boolean flag = caseService.updateCase(c);
            if(flag)
                return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getCase")
    public ResponseResult getCase(Integer id){
        if(id != null){
            Case c = caseService.getCase(id);
            if(!BeanUtil.isNull(c))
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(c).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

    @GetMapping("listPageCase")
    @Transactional
    public ResponsePageResult listPageCase(Integer page, Integer limit){
        if(page != null && limit != null){
            List<Case> list = caseService.listPageCase(page, limit);
            if(list.size() > 0) {
                int count = caseService.countCase();
                return ResponsePageResult.builder().code(0).msg("查询成功").count(count).data(list).build();
            }
        }
        return ResponsePageResult.builder().code(0).msg("数据为空").count(0).data(null).build();
    }

    @GetMapping("listAllCase")
    public ResponseResult listAllCase(){
        List<Case> list = caseService.listAllCase();
        if(list.size() > 0)
            return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(list).build();
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }
}
