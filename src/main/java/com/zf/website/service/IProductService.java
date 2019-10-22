package com.zf.website.service;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Product;

import java.util.List;

public interface IProductService {

    boolean saveBanner(Banner banner);

    boolean deleteBanner(Integer id);

    boolean updateBanner(Banner banner);

    boolean usedBanner(Integer id);

    boolean unsedBanner();

    Banner getBanner(Integer id);

    Banner getUsedBanner();

    List<Banner> getUsedListBanner();

    List<Banner> listBanner();

    boolean saveProduct(Product product);

    boolean deleteProduct(Integer id);

    boolean updateProduct(Product product);

    int countProduct();

    List<Product> listAllProduct();

    List<Product> listPageProduct(Integer page,Integer limit);

    Product getProduct(Integer id);

    Product getPreProduct(Integer id);

    Product getNextProduct(Integer id);

}
