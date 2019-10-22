package com.zf.website.mapper;

import com.zf.website.bean.Banner;
import com.zf.website.bean.CompanyProfile;
import com.zf.website.bean.Course;
import com.zf.website.bean.Recruit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/28 10:56
 */
public interface AboutMapper {

    @Insert("insert into t_banner values(null,#{title},#{describe},#{realPath},#{relativePath},#{used},4)")
    int saveBanner(Banner banner);

    @Delete("delete from t_banner where id=#{id} and scope=4")
    int deleteBanner(Integer id);

    @Update("update t_banner set title=#{title},`describe`=#{describe} where id=#{id} and scope=4")
    int updateBanner(Banner banner);

    @Update("update t_banner set used=1 where id=#{id} and scope=4")
    int usedBanner(Integer id);

    @Update("update t_banner set used=0 where scope=4")
    int unsedBanner();

    @Select("select * from t_banner where id=#{id} and scope=4")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    Banner getBanner(Integer id);

    @Select("select * from t_banner where used=1 and scope=4")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    Banner getUsedBanner();

    @Select("select * from t_banner where used=1 and scope=4")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    List<Banner> getUsedListBanner();

    @Select("select * from t_banner where scope=4")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    List<Banner> listBanner();

    @Insert("insert into t_company_profile values(null,#{content})")
    int saveCompanyProfile(CompanyProfile companyProfile);

    @Delete("delete from t_company_profile where id=#{id}")
    int deleteCompanyProfile(Integer id);

    @Update("update t_company_profile set content=#{content} where id=#{id}")
    int updateCompanyProfile(CompanyProfile companyProfile);

    @Select("select * from t_company_profile")
    CompanyProfile getCompanyProfile();

    @Insert("insert into t_recruit values(null,#{title},#{describe})")
    int saveRecruit(Recruit recruit);

    @Delete("delete from t_recruit where id=#{id}")
    int deleteRecruit(Integer id);

    @Update("update t_recruit set title=#{title},`describe`=#{describe} where id=#{id}")
    int updateRecruit(Recruit recruit);

    @Select("select * from t_recruit where id=#{id}")
    Recruit getRecruit(Integer id);

    @Select("select * from t_recruit")
    List<Recruit> listAllRecruit();

    @Select("select * from t_recruit limit #{page},#{limit}")
    List<Recruit> listRecruit(@Param("page") Integer page,@Param("limit") Integer limit);

    @Select("select count(id) from t_recruit")
    int countRecruit();

    @Insert("insert into t_course values(null,#{time},#{imgRelativePath},#{imgRealPath},#{describe})")
    int saveCourse(Course course);

    @Delete("delete from t_course where id=#{id}")
    int deleteCourse(Integer id);

    @Update("update t_course set time=#{time},img_relative_path=#{imgRelativePath},img_real_path=#{imgRealPath},`describe`=#{describe} where id=#{id}")
    int updateCourse(Course course);

    @Select("select * from t_course where id=#{id}")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    Course getCourse(Integer id);

    @Select("select * from t_course")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    List<Course> listAllCourse();

    @Select("select * from t_course limit #{page},#{limit}")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    List<Course> listCourse(@Param("page") Integer page,@Param("limit") Integer limit);

    @Select("select count(id) from t_course")
    int countCourse();
}
