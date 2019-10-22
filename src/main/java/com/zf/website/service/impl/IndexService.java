package com.zf.website.service.impl;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Logo;
import com.zf.website.mapper.IndexMapper;
import com.zf.website.service.IIndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Model:首页管理业务逻辑层
 * Author:Tr9(韩峰)
 * Description:包含logo设置、banner设置
 * Time: 2019/9/24 9:15
 */
@Service
@Transactional
public class IndexService implements IIndexService {

    @Resource
    private IndexMapper indexMapper;

    @Override
    public boolean saveLogo(Logo logo) {
        return indexMapper.saveLogo(logo) > 0 ? true : false;
    }

    @Override
    public boolean deleteLogo(Integer id) {
        Logo logo = indexMapper.getLogo(id);
        File file = new File(logo.getRealPath());
        boolean flag = file.delete();
        return indexMapper.deleteLogo(id) > 0 && flag ? true : false;
    }

    @Override
    public List<Logo> listLogo() {
        return indexMapper.listLogo();
    }

    @Override
    public Logo getUsedLogo() {
        return indexMapper.getUsedLogo();
    }

    @Override
    public boolean usedLogo(Integer id) {
        return indexMapper.unsedLogo() + indexMapper.usedLogo(id) > 0 ? true : false;
    }

    @Override
    public boolean unsedLogo() {
        return indexMapper.unsedLogo() > 0 ? true : false;
    }

    @Override
    public boolean saveBanner(Banner banner) {
        return indexMapper.saveBanner(banner) > 0 ? true : false;
    }

    @Override
    public boolean deleteBanner(Integer id) {
        Banner banner = indexMapper.getBanner(id);
        File file = new File(banner.getRealPath());
        boolean flag = file.delete();
        return flag && indexMapper.deleteBanner(id) > 0 ? true : false;
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return indexMapper.updateBanner(banner) > 0 ? true : false;
    }

    @Override
    public boolean usedBanner(Integer id) {
        return indexMapper.usedBanner(id) > 0 ? true : false;
    }

    @Override
    public boolean unsedBanner(Integer id) {
        return indexMapper.unsedBanner(id) > 0 ? true : false;
    }

    @Override
    public Banner getBanner(Integer id) {
        return indexMapper.getBanner(id);
    }

    @Override
    public List<Banner> getUsedListBanner() {
        return indexMapper.getUsedListBanner();
    }

    @Override
    public List<Banner> listBanner() {
        return indexMapper.listBanner();
    }
}
