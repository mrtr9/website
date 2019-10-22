package com.zf.website.controller;

import com.zf.website.ResponsePageResult;
import com.zf.website.ResponseResult;
import com.zf.website.bean.Banner;
import com.zf.website.bean.News;
import com.zf.website.bean.dto.ImageUploadDTO;
import com.zf.website.config.FileUploadConfig;
import com.zf.website.service.INewsService;
import com.zf.website.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/18 11:18
 */
@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private INewsService newsService;

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
            boolean flag = newsService.saveBanner(banner);
            if (flag)
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("操作成功").data(Boolean.TRUE).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteBanner")
    public ResponseResult deleteBanner(Integer id) {
        boolean flag = newsService.deleteBanner(id);
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
        boolean flag = newsService.updateBanner(banner);
        if (flag)
            return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @PutMapping("usedBanner")
    public ResponseResult usedBanner(Integer id) {
        boolean flag = newsService.usedBanner(id);
        if (flag)
            return ResponseResult.builder().code(200).message("启用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("启用失败").data(Boolean.FALSE).build();
    }

    @PutMapping("unsedBanner")
    public ResponseResult unsedBanner() {
        boolean flag = newsService.unsedBanner();
        if (flag)
            return ResponseResult.builder().code(200).message("停用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("停用失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getBanner")
    public ResponseResult getBanner(Integer id) {
        Banner banner = newsService.getBanner(id);
        if (banner != null)
            return ResponseResult.builder().code(200).message("查询成功").data(banner).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getUsedListBanner")
    public ResponseResult getUsedListBanner() {
        List<Banner> list = newsService.getUsedListBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("listBanner")
    public ResponseResult listBanner() {
        List<Banner> list = newsService.listBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @PostMapping("saveNews")
    public ResponseResult saveNews(News news,MultipartFile file) throws IOException {
        if (!BeanUtil.isNull(news)) {
            if (file != null) {
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getNewsRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getNewsRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getNewsRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                file.transferTo(new File(realPath));
                news.setImgRealPath(realPath);
                news.setImgRelativePath(relativePath);
            }
            news.setPublishDate(LocalDate.now());
            boolean flag = newsService.saveNews(news);
            if (flag)
                return ResponseResult.builder().code(200).message("保存成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("保存失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteNews")
    public ResponseResult deleteNews(Integer id) {
        if (id != null) {
            boolean flag = newsService.deleteNews(id);
            if (flag)
                return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateNews")
    public ResponseResult updateNews(News news,MultipartFile file) throws IOException {
        if(!BeanUtil.isNull(news)){
            if (file != null) {
                String fileSourcePath = news.getImgRealPath();
                if (fileSourcePath != null) {
                    new File(fileSourcePath).delete();
                }
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getNewsRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getNewsRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getNewsRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                file.transferTo(new File(realPath));
                news.setImgRealPath(realPath);
                news.setImgRelativePath(relativePath);
            }
            news.setPublishDate(LocalDate.now());
            boolean flag = newsService.updateNews(news);
            if(flag)
                return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getNews")
    public ResponseResult getNews(Integer id){
        if(id != null){
            News news = newsService.getNews(id);
            if(!BeanUtil.isNull(news))
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(news).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

    @GetMapping("listPageNews")
    @Transactional
    public ResponsePageResult listPageNews(Integer page,Integer limit){
        if(page != null && limit != null){
            List<News> list = newsService.listPageNews(page, limit);
            if(list.size() > 0) {
                int count = newsService.countNews();
                return ResponsePageResult.builder().code(0).msg("查询成功").count(count).data(list).build();
            }
        }
        return ResponsePageResult.builder().code(0).msg("数据为空").count(0).data(null).build();
    }

    @GetMapping("listAllNews")
    public ResponseResult listAllNews(){
        List<News> list = newsService.listAllNews();
        if(list.size() > 0)
            return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(list).build();
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

    @GetMapping("getPreNews")
    public ResponseResult getPreProduct(String date) {
        if(date != null){
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            News news = newsService.getPreNews(localDate);
            if(!BeanUtil.isNull(news))
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(news).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

    @GetMapping("getNextNews")
    public ResponseResult getNextProduct(String date) {
        if(date != null){
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            News news = newsService.getNextNews(localDate);
            if(!BeanUtil.isNull(news))
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(news).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

    @PostMapping("upload")
    public ResponseResult upload(MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            String realPath = fileUploadConfig.getNewsRealPath() + "" + fileName;
            String relativePath = fileUploadConfig.getNewsRelativePath() + "" + fileName;
            File filePath = new File(fileUploadConfig.getNewsRealPath());
            if (!filePath.exists())
                filePath.mkdirs();
            file.transferTo(new File(realPath));
            ImageUploadDTO imageUploadDTO = ImageUploadDTO.builder().title(fileName).src(relativePath).realPath(realPath).build();
            return ResponseResult.builder().code(0).message("上传成功").data(imageUploadDTO).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("上传失败").data(Boolean.FALSE).build();
    }
}
