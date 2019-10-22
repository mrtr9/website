package com.zf.website.mapper;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Case;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CaseMapper {

    @Insert("insert into t_banner values(null,#{title},#{describe},#{realPath},#{relativePath},#{used},3)")
    int saveBanner(Banner banner);

    @Delete("delete from t_banner where id=#{id} and scope=3")
    int deleteBanner(Integer id);

    @Update("update t_banner set title=#{title},`describe`=#{describe} where id=#{id} and scope=3")
    int updateBanner(Banner banner);

    @Update("update t_banner set used=1 where id=#{id} and scope=3")
    int usedBanner(Integer id);

    @Update("update t_banner set used=0 where scope=3")
    int unsedBanner();

    @Select("select * from t_banner where id=#{id} and scope=3")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    Banner getBanner(Integer id);

    @Select("select * from t_banner where used=1 and scope=3")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    Banner getUsedBanner();

    @Select("select * from t_banner where used=1 and scope=3")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    List<Banner> getUsedListBanner();


    @Select("select * from t_banner where scope=3")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    List<Banner> listBanner();

    @Insert("insert into t_case values(null,#{title},#{imgRelativePath},#{imgRealPath},#{describe},#{date})")
    int saveCase(Case c);

    @Delete("delete from t_case where id=#{id}")
    int deleteCase(Integer id);

    @Update("update t_case set title=#{title},img_relative_path=#{imgRelativePath},img_real_path=#{imgRealPath},`describe`=#{describe},`date`=#{date} where id=#{id}")
    int updateCase(Case c);

    @Select("select count(id) from t_case")
    int countCase();

    @Select("select * from t_case where id=#{id}")
    @Results({
            @Result(column = "img_real_path", property = "imgRealPath"),
            @Result(column = "img_relative_path", property = "imgRelativePath"),
    })
    Case getCase(Integer id);

    @Select("SELECT * FROM t_case ORDER BY date DESC LIMIT #{page},#{limit}")
    @Results({
            @Result(column = "img_real_path", property = "imgRealPath"),
            @Result(column = "img_relative_path", property = "imgRelativePath"),
    })
    List<Case> listPageCase(@Param("page") Integer page,@Param("limit") Integer limit);

    @Select("SELECT * FROM t_case ORDER BY date DESC")
    @Results({
            @Result(column = "img_real_path", property = "imgRealPath"),
            @Result(column = "img_relative_path", property = "imgRelativePath"),
    })
    List<Case> listAllCase();
}
