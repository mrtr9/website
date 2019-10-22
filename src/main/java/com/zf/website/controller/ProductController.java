package com.zf.website.controller;

import com.zf.website.ResponsePageResult;
import com.zf.website.ResponseResult;
import com.zf.website.bean.Banner;
import com.zf.website.bean.Product;
import com.zf.website.config.FileUploadConfig;
import com.zf.website.service.IProductService;
import com.zf.website.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/15 10:10
 */
@RestController
@RequestMapping("product")
@Validated
public class ProductController {

    @Autowired
    private IProductService productService;

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
            boolean flag = productService.saveBanner(banner);
            if (flag)
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("操作成功").data(Boolean.TRUE).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.builder().code(200).message("操作失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteBanner")
    public ResponseResult deleteBanner(Integer id) {
        boolean flag = productService.deleteBanner(id);
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
        boolean flag = productService.updateBanner(banner);
        if (flag)
            return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @PutMapping("usedBanner")
    public ResponseResult usedBanner(Integer id) {
        boolean flag = productService.usedBanner(id);
        if (flag)
            return ResponseResult.builder().code(200).message("启用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("启用失败").data(Boolean.FALSE).build();
    }

    @PutMapping("unsedBanner")
    public ResponseResult unsedBanner() {
        boolean flag = productService.unsedBanner();
        if (flag)
            return ResponseResult.builder().code(200).message("停用成功").data(Boolean.TRUE).build();
        return ResponseResult.builder().code(200).message("停用失败").data(Boolean.FALSE).build();
    }

    @GetMapping("getBanner")
    public ResponseResult getBanner(Integer id) {
        Banner banner = productService.getBanner(id);
        if (banner != null)
            return ResponseResult.builder().code(200).message("查询成功").data(banner).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getUsedListBanner")
    public ResponseResult getUsedListBanner() {
        List<Banner> list = productService.getUsedListBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("listBanner")
    public ResponseResult listBanner() {
        List<Banner> list = productService.listBanner();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @PostMapping("saveProduct")
    public ResponseResult saveProduct(Product product, MultipartFile file) throws IOException {
        if (!BeanUtil.isNull(product)) {
            if (file != null) {
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getProductRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getProductRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getProductRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                file.transferTo(new File(realPath));
                product.setImgRealPath(realPath);
                product.setImgRelativePath(relativePath);
            }
            boolean flag = productService.saveProduct(product);
            if (flag)
                return ResponseResult.builder().code(200).message("保存成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("保存失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping("deleteProduct")
    public ResponseResult deleteProduct(Integer id) {
        if (id != null) {
            boolean flag = productService.deleteProduct(id);
            if (flag)
                return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping("updateProduct")
    public ResponseResult updateProduct(Product product, MultipartFile file) throws IOException {
        if (!BeanUtil.isNull(product)) {
            if (file != null) {
                String fileSourcePath = product.getImgRealPath();
                if (fileSourcePath != null) {
                    new File(fileSourcePath).delete();
                }
                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                String realPath = fileUploadConfig.getProductRealPath() + "" + fileName;
                String relativePath = fileUploadConfig.getProductRelativePath() + "" + fileName;
                File filePath = new File(fileUploadConfig.getProductRealPath());
                if (!filePath.exists())
                    filePath.mkdirs();
                file.transferTo(new File(realPath));
                product.setImgRealPath(realPath);
                product.setImgRelativePath(relativePath);
            }
            boolean flag = productService.updateProduct(product);
            if (flag)
                return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping("listAllProduct")
    public ResponseResult listAllProduct() {
        List<Product> list = productService.listAllProduct();
        if (list.size() > 0)
            return ResponseResult.builder().code(200).message("查询成功").data(list).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("listPageProduct")
    @Transactional
    public ResponsePageResult listPageProduct(Integer page, Integer limit) {
        List<Product> list = productService.listPageProduct(page, limit);
        int count = productService.countProduct();
        if (list.size() > 0)
            return ResponsePageResult.builder().code(0).msg("查询成功").count(count).data(list).build();
        return ResponsePageResult.builder().code(0).msg("数据为空").count(0).data(null).build();
    }

    @GetMapping("getProduct")
    public ResponseResult getProduct(Integer id) {
        if (id != null) {
            Product product = productService.getProduct(id);
            if (!BeanUtil.isNull(product))
                return ResponseResult.builder().code(200).message("查询成功").data(product).build();
        }
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }

    @GetMapping("getPreProduct")
    public ResponseResult getPreProduct(Integer id) {
        if(id != null){
            Product product = productService.getPreProduct(id);
            if(product != null)
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(product).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

    @GetMapping("getNextProduct")
    public ResponseResult getNextProduct(Integer id) {
        if(id != null){
            Product product = productService.getNextProduct(id);
            if(product != null)
                return ResponseResult.builder().code(HttpStatus.OK.value()).message("查询成功").data(product).build();
        }
        return ResponseResult.builder().code(HttpStatus.OK.value()).message("数据为空").data(null).build();
    }

}
