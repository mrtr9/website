package com.zf.website.service.impl;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Product;
import com.zf.website.mapper.ProductMapper;
import com.zf.website.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/15 9:59
 */
@Service
@Transactional
public class ProductService implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public boolean saveBanner(Banner banner) {
        return productMapper.saveBanner(banner) > 0 ? true : false;
    }

    @Override
    public boolean deleteBanner(Integer id) {
        return productMapper.deleteBanner(id) > 0 ? true : false;
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return productMapper.updateBanner(banner) > 0 ? true : false;
    }

    @Override
    public boolean usedBanner(Integer id) {
        return productMapper.usedBanner(id) > 0 ? true : false;
    }

    @Override
    public boolean unsedBanner() {
        return productMapper.unsedBanner() > 0 ? true : false;
    }

    @Override
    public Banner getBanner(Integer id) {
        return productMapper.getBanner(id);
    }

    @Override
    public Banner getUsedBanner() {
        return productMapper.getUsedBanner();
    }

    @Override
    public List<Banner> getUsedListBanner() {
        return productMapper.getUsedListBanner();
    }

    @Override
    public List<Banner> listBanner() {
        return productMapper.listBanner();
    }

    @Override
    public boolean saveProduct(Product product) {
        return productMapper.saveProduct(product) > 0;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return productMapper.deleteProduct(id) > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        return productMapper.updateProduct(product) > 0;
    }

    @Override
    public int countProduct() {
        return productMapper.countProduct();
    }

    @Override
    public List<Product> listAllProduct() {
        return productMapper.listAllProduct();
    }

    @Override
    public List<Product> listPageProduct(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return productMapper.listPageProduct(page, limit);
    }

    @Override
    public Product getProduct(Integer id) {
        return productMapper.getProduct(id);
    }

    @Override
    public Product getPreProduct(Integer id) {
        return productMapper.getPreProduct(id);
    }

    @Override
    public Product getNextProduct(Integer id) {
        return productMapper.getNextProduct(id);
    }
}
