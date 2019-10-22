package com.zf.website.controller;

import com.zf.website.ResponsePageResult;
import com.zf.website.ResponseResult;
import com.zf.website.bean.Banner;
import com.zf.website.bean.CompanyProfile;
import com.zf.website.bean.Course;
import com.zf.website.bean.Recruit;
import com.zf.website.bean.dto.ImageUploadDTO;
import com.zf.website.config.FileUploadConfig;
import com.zf.website.service.IAboutService;
import com.zf.website.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/28 11:21
 */
@RestController
@RequestMapping("about")
public class AboutController {

    @Autowired
    private IAboutService aboutService;

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
            boolean flag = aboutService.saveBanner(banner);
            if (flag)
                return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteBanner")
    public ResponseResult deleteBanner(Integer id) {
        boolean flag = aboutService.deleteBanner(id);
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
        boolean flag = aboutService.updateBanner(banner);
        if (flag)
            return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @PutMapping("usedBanner")
    public ResponseResult usedBanner(Integer id) {
        boolean flag = aboutService.usedBanner(id);
        if (flag)
            return ResponseResult.builder().code(200).message("启用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("启用失败").data(Boolean.FALSE).build();
    }

    @PutMapping("unsedBanner")
    public ResponseResult unsedBanner() {
        boolean flag = aboutService.unsedBanner();
        if (flag)
            return ResponseResult.builder().code(200).message("停用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("停用失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getBanner")
    public ResponseResult getBanner(Integer id) {
        Banner banner = aboutService.getBanner(id);
        if (banner != null)
            return ResponseResult.builder().code(200).message("查询成功").data(banner).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getUsedListBanner")
    public ResponseResult getUsedListBanner() {
        List<Banner> list = aboutService.getUsedListBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("listBanner")
    public ResponseResult listBanner() {
        List<Banner> list = aboutService.listBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @PostMapping("companyProfileUploader")
    public ResponseResult companyProfileUploader(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        String realPath = fileUploadConfig.getAboutRealPath() + "" + fileName;
        String relativePath = fileUploadConfig.getAboutRelativePath() + "" + fileName;
        File filePath = new File(fileUploadConfig.getAboutRealPath());
        if (!filePath.exists())
            filePath.mkdirs();
        try {
            file.transferTo(new File(realPath));
            ImageUploadDTO imageUploadDTO = ImageUploadDTO.builder().title(file.getOriginalFilename()).src(relativePath).realPath(realPath).build();
            return ResponseResult.builder().code(0).message("上传成功").data(imageUploadDTO).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(0).message("上传失败").data(Boolean.FALSE).build();
    }

    @PostMapping("saveCompanyProfile")
    public ResponseResult saveCompanyProfile(CompanyProfile companyProfile) {
        boolean flag = aboutService.saveCompanyProfile(companyProfile);
        if (flag)
            return ResponseResult.builder().code(200).message("保存成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("保存失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteCompanyProfile")
    public ResponseResult deleteCompanyProfile(Integer id) {
        boolean flag = aboutService.deleteCompanyProfile(id);
        if (flag)
            return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateCompanyProfile")
    public ResponseResult updateCompanyProfile(CompanyProfile companyProfile) {
        boolean flag = aboutService.updateCompanyProfile(companyProfile);
        if (flag)
            return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getCompanyProfile")
    public ResponseResult getCompanyProfile() {
        CompanyProfile companyProfile = aboutService.getCompanyProfile();
        if (BeanUtil.isNull(companyProfile))
            return ResponseResult.builder().code(200).message("数据为空").data(null).build();
        return ResponseResult.builder().code(200).message("查询成功").data(companyProfile).build();
    }

    @PostMapping("saveRecruit")
    public ResponseResult saveRecruit(Recruit recruit) {
        if (!BeanUtil.isNull(recruit)) {
            boolean flag = aboutService.saveRecruit(recruit);
            if (flag)
                return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteRecruit")
    public ResponseResult deleteRecruit(Integer id) {
        if (id != null) {
            boolean flag = aboutService.deleteRecruit(id);
            if (flag)
                return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateRecruit")
    public ResponseResult updateRecruit(Recruit recruit) {
        if (!BeanUtil.isNull(recruit)) {
            boolean flag = aboutService.updateRecruit(recruit);
            if (flag)
                return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getRecruit")
    public ResponseResult getRecruit(Integer id) {
        if (id != null) {
            Recruit recruit = aboutService.getRecruit(id);
            if (!BeanUtil.isNull(recruit))
                return ResponseResult.builder().code(200).message("查询成功").data(recruit).build();
            return ResponseResult.builder().code(200).message("数据为空").data(null).build();
        }
        return ResponseResult.builder().code(200).message("查询失败").data(Boolean.FALSE).build();
    }

    @GetMapping("listRecruit")
    public ResponsePageResult listRecruit(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<Recruit> list = aboutService.listRecruit(page, limit);
        int count = aboutService.countRecruit();
        if (list.size() > 0)
            return ResponsePageResult.builder().code(0).msg("查询成功").data(list).count(count).build();
        return ResponsePageResult.builder().code(0).msg("数据为空").data(null).count(0).build();
    }

    @GetMapping("listAllRecruit")
    public ResponsePageResult listRecruit() {
        List<Recruit> list = aboutService.listAllRecruit();
        if (list.size() > 0)
            return ResponsePageResult.builder().code(0).msg("查询成功").data(list).count(list.size()).build();
        return ResponsePageResult.builder().code(0).msg("数据为空").data(null).count(0).build();
    }

    @PostMapping("saveCourse")
    public ResponseResult saveCourse(Course course, MultipartFile file) {
        if (!BeanUtil.isNull(course) && file != null) {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            String realPath = fileUploadConfig.getAboutRealPath() + "" + fileName;
            String relativePath = fileUploadConfig.getAboutRelativePath() + "" + fileName;
            File filePath = new File(fileUploadConfig.getAboutRealPath());
            if (!filePath.exists())
                filePath.mkdirs();
            try {
                file.transferTo(new File(realPath));
                course.setImgRealPath(realPath);
                course.setImgRelativePath(relativePath);
                boolean flag = aboutService.saveCourse(course);
                if (flag)
                    return ResponseResult.builder().code(200).message("操作成功").data(Boolean.TRUE).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteCourse")
    public ResponseResult deleteCourse(Integer id) {
        if (id != null) {
            boolean flag = aboutService.deleteCourse(id);
            if (flag)
                return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateCourse")
    public ResponseResult updateRecruit(Course course, MultipartFile file) {
        if (!BeanUtil.isNull(course)) {
            if (file != null) {
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getAboutRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getAboutRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getAboutRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                try {
                    if(course.getImgRealPath() != null){
                        new File(course.getImgRealPath()).delete();
                    }
                    file.transferTo(new File(realPath));
                    course.setImgRealPath(realPath);
                    course.setImgRelativePath(relativePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = aboutService.updateCourse(course);
            if (flag)
                return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();

        }
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getCourse")
    public ResponseResult getCourse(Integer id) {
        if (id != null) {
            Course course = aboutService.getCourse(id);
            if (!BeanUtil.isNull(course))
                return ResponseResult.builder().code(200).message("查询成功").data(course).build();
            return ResponseResult.builder().code(200).message("数据为空").data(null).build();
        }
        return ResponseResult.builder().code(200).message("查询失败").data(Boolean.FALSE).build();
    }

    @GetMapping("listCourse")
    public ResponsePageResult listCourse(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<Course> list = aboutService.listCourse(page, limit);
        int count = aboutService.countCourse();
        if (list.size() > 0)
            return ResponsePageResult.builder().code(0).msg("查询成功").data(list).count(count).build();
        return ResponsePageResult.builder().code(0).msg("数据为空").data(null).count(0).build();
    }

    @GetMapping("listAllCourse")
    public ResponsePageResult listCourse() {
        List<Course> list = aboutService.listAllCourse();
        if (list.size() > 0)
            return ResponsePageResult.builder().code(0).msg("查询成功").data(list).count(list.size()).build();
        return ResponsePageResult.builder().code(0).msg("数据为空").data(null).count(0).build();
    }

}
