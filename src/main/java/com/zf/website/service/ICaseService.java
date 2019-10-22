package com.zf.website.service;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Case;

import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/22 10:12
 */
public interface ICaseService {

    boolean saveBanner(Banner banner);

    boolean deleteBanner(Integer id);

    boolean updateBanner(Banner banner);

    boolean usedBanner(Integer id);

    boolean unsedBanner();

    Banner getBanner(Integer id);

    Banner getUsedBanner();

    List<Banner> getUsedListBanner();

    List<Banner> listBanner();

    boolean saveCase(Case c);

    boolean deleteCase(Integer id);

    boolean updateCase(Case c);

    int countCase();

    Case getCase(Integer id);

    List<Case> listPageCase(Integer page,Integer limit);

    List<Case> listAllCase();
}
