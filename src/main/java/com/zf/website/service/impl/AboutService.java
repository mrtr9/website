package com.zf.website.service.impl;

import com.zf.website.bean.Banner;
import com.zf.website.bean.CompanyProfile;
import com.zf.website.bean.Course;
import com.zf.website.bean.Recruit;
import com.zf.website.mapper.AboutMapper;
import com.zf.website.service.IAboutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/28 10:58
 */
@Service
@Transactional
public class AboutService implements IAboutService {

    @Resource
    private AboutMapper aboutMapper;

    @Override
    public boolean saveBanner(Banner banner) {
        return aboutMapper.saveBanner(banner) > 0 ? true : false;
    }

    @Override
    public boolean deleteBanner(Integer id) {
        Banner banner = aboutMapper.getBanner(id);
        File file = new File(banner.getRealPath());
        boolean flag = file.delete();
        return flag && aboutMapper.deleteBanner(id) > 0 ? true : false;
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return aboutMapper.updateBanner(banner) > 0 ? true : false;
    }

    @Override
    public boolean usedBanner(Integer id) {
        return aboutMapper.unsedBanner() + aboutMapper.usedBanner(id) > 0 ? true : false;
    }

    @Override
    public boolean unsedBanner() {
        return aboutMapper.unsedBanner() > 0 ? true : false;
    }

    @Override
    public Banner getBanner(Integer id) {
        return aboutMapper.getBanner(id);
    }

    @Override
    public Banner getUsedBanner() {
        return aboutMapper.getUsedBanner();
    }

    @Override
    public List<Banner> getUsedListBanner() {
        return aboutMapper.getUsedListBanner();
    }

    @Override
    public List<Banner> listBanner() {
        return aboutMapper.listBanner();
    }

    @Override
    public boolean saveCompanyProfile(CompanyProfile companyProfile) {
        return aboutMapper.saveCompanyProfile(companyProfile) > 0 ? true : false;
    }

    @Override
    public boolean deleteCompanyProfile(Integer id) {
        return aboutMapper.deleteCompanyProfile(id) > 0 ? true : false;
    }

    @Override
    public boolean updateCompanyProfile(CompanyProfile companyProfile) {
        return aboutMapper.updateCompanyProfile(companyProfile) > 0 ? true : false;
    }

    @Override
    public CompanyProfile getCompanyProfile() {
        return aboutMapper.getCompanyProfile();
    }

    @Override
    public boolean saveRecruit(Recruit recruit) {
        return aboutMapper.saveRecruit(recruit) > 0 ? true : false;
    }

    @Override
    public boolean deleteRecruit(Integer id) {
        return aboutMapper.deleteRecruit(id) > 0 ? true : false;
    }

    @Override
    public boolean updateRecruit(Recruit recruit) {
        return aboutMapper.updateRecruit(recruit) > 0 ? true : false;
    }

    @Override
    public Recruit getRecruit(Integer id) {
        return aboutMapper.getRecruit(id);
    }

    @Override
    public List<Recruit> listAllRecruit() {
        return aboutMapper.listAllRecruit();
    }

    @Override
    public List<Recruit> listRecruit(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return aboutMapper.listRecruit(page, limit);
    }

    @Override
    public int countRecruit() {
        return aboutMapper.countRecruit();
    }

    @Override
    public boolean saveCourse(Course course) {
        return aboutMapper.saveCourse(course) > 0 ? true : false;
    }

    @Override
    public boolean deleteCourse(Integer id) {
        return aboutMapper.deleteCourse(id) > 0 ? true : false;
    }

    @Override
    public boolean updateCourse(Course course) {
        return aboutMapper.updateCourse(course) > 0 ? true : false;
    }

    @Override
    public Course getCourse(Integer id) {
        return aboutMapper.getCourse(id);
    }

    @Override
    public List<Course> listAllCourse() {
        return aboutMapper.listAllCourse();
    }

    @Override
    public List<Course> listCourse(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return aboutMapper.listCourse(page, limit);
    }

    @Override
    public int countCourse() {
        return aboutMapper.countCourse();
    }
}
