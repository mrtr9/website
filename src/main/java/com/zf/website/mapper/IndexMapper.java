package com.zf.website.mapper;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Logo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Model:首页管理Mapper
 * Author:Tr9(韩峰)
 * Description:包含logo设置、banner设置的增删改查操作
 * Time: 2019/9/23 13:48
 */
@Mapper
public interface IndexMapper {

    @Select("select * from t_logo where id=#{id}")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath"),
    })
    Logo getLogo(Integer id);

    @Insert("insert into t_logo values(null,#{realPath},#{relativePath},#{used})")
    int saveLogo(Logo logo);

    @Delete("delete from t_logo where id=#{id}")
    int deleteLogo(Integer id);

    @Select("select * from t_logo")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath"),
    })
    List<Logo> listLogo();

    @Update("update t_logo set used=0")
    int unsedLogo();

    @Update("update t_logo set used=1 where id=#{id}")
    int usedLogo(Integer id);

    @Select("select * from t_logo where used=1")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath"),
    })
    Logo getUsedLogo();

    @Insert("insert into t_banner values(null,#{title},#{describe},#{realPath},#{relativePath},#{used},0)")
    int saveBanner(Banner banner);

    @Delete("delete from t_banner where id=#{id} and scope=0")
    int deleteBanner(Integer id);

    @Update("update t_banner set title=#{title},`describe`=#{describe} where id=#{id} and scope=0")
    int updateBanner(Banner banner);

    @Update("update t_banner set used=1 where id=#{id} and scope=0")
    int usedBanner(Integer id);

    @Update("update t_banner set used=0 where id=#{id} and scope=0")
    int unsedBanner(Integer id);

    @Select("select * from t_banner where id=#{id} and scope=0")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    Banner getBanner(Integer id);

    @Select("select * from t_banner where used=1 and scope=0")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    List<Banner> getUsedListBanner();

    @Select("select * from t_banner where scope=0")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    List<Banner> listBanner();
}