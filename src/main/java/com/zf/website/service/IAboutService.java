package com.zf.website.service;

import com.zf.website.bean.Banner;
import com.zf.website.bean.CompanyProfile;
import com.zf.website.bean.Course;
import com.zf.website.bean.Recruit;

import java.util.List;

public interface IAboutService {

    boolean saveBanner(Banner banner);

    boolean deleteBanner(Integer id);

    boolean updateBanner(Banner banner);

    boolean usedBanner(Integer id);

    boolean unsedBanner();

    Banner getBanner(Integer id);

    Banner getUsedBanner();

    List<Banner> getUsedListBanner();

    List<Banner> listBanner();

    boolean saveCompanyProfile(CompanyProfile companyProfile);

    boolean deleteCompanyProfile(Integer id);

    boolean updateCompanyProfile(CompanyProfile companyProfile);

    CompanyProfile getCompanyProfile();

    boolean saveRecruit(Recruit recruit);

    boolean deleteRecruit(Integer id);

    boolean updateRecruit(Recruit recruit);

    Recruit getRecruit(Integer id);

    List<Recruit> listAllRecruit();

    List<Recruit> listRecruit(Integer page,Integer limit);

    int countRecruit();

    boolean saveCourse(Course course);

    boolean deleteCourse(Integer id);

    boolean updateCourse(Course course);

    Course getCourse(Integer id);

    List<Course> listAllCourse();

    List<Course> listCourse(Integer page,Integer limit);

    int countCourse();
}
