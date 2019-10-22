package com.zf.website.service.impl;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Case;
import com.zf.website.mapper.CaseMapper;
import com.zf.website.service.ICaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/22 10:19
 */
@Service
@Transactional
public class CaseServiceImpl implements ICaseService {

    @Resource
    private CaseMapper caseMapper;

    @Override
    public boolean saveBanner(Banner banner) {
        return caseMapper.saveBanner(banner) > 0;
    }

    @Override
    public boolean deleteBanner(Integer id) {
        return caseMapper.deleteBanner(id) > 0;
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return caseMapper.updateBanner(banner) > 0;
    }

    @Override
    public boolean usedBanner(Integer id) {
        return caseMapper.usedBanner(id) > 0;
    }

    @Override
    public boolean unsedBanner() {
        return caseMapper.unsedBanner() > 0;
    }

    @Override
    public Banner getBanner(Integer id) {
        return caseMapper.getBanner(id);
    }

    @Override
    public Banner getUsedBanner() {
        return caseMapper.getUsedBanner();
    }

    @Override
    public List<Banner> getUsedListBanner() {
        return caseMapper.getUsedListBanner();
    }

    @Override
    public List<Banner> listBanner() {
        return caseMapper.listBanner();
    }

    @Override
    public boolean saveCase(Case c) {
        return caseMapper.saveCase(c) > 0;
    }

    @Override
    public boolean deleteCase(Integer id) {
        return caseMapper.deleteCase(id) > 0;
    }

    @Override
    public boolean updateCase(Case c) {
        return caseMapper.updateCase(c) > 0;
    }

    @Override
    public int countCase() {
        return caseMapper.countCase();
    }

    @Override
    public Case getCase(Integer id) {
        return caseMapper.getCase(id);
    }

    @Override
    public List<Case> listPageCase(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return caseMapper.listPageCase(page, limit);
    }

    @Override
    public List<Case> listAllCase() {
        return caseMapper.listAllCase();
    }
}
