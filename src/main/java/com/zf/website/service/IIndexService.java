package com.zf.website.service;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Logo;

import java.util.List;

public interface IIndexService {

    boolean saveLogo(Logo logo);

    boolean deleteLogo(Integer id);

    List<Logo> listLogo();

    Logo getUsedLogo();

    boolean usedLogo(Integer id);

    boolean unsedLogo();

    boolean saveBanner(Banner banner);

    boolean deleteBanner(Integer id);

    boolean updateBanner(Banner banner);

    boolean usedBanner(Integer id);

    boolean unsedBanner(Integer id);

    Banner getBanner(Integer id);

    List<Banner> getUsedListBanner();

    List<Banner> listBanner();
}
