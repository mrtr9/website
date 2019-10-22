package com.zf.website.mapper;

import com.zf.website.bean.Banner;
import com.zf.website.bean.News;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface NewsMapper {

    @Insert("insert into t_banner values(null,#{title},#{describe},#{realPath},#{relativePath},#{used},2)")
    int saveBanner(Banner banner);

    @Delete("delete from t_banner where id=#{id} and scope=2")
    int deleteBanner(Integer id);

    @Update("update t_banner set title=#{title},`describe`=#{describe} where id=#{id} and scope=2")
    int updateBanner(Banner banner);

    @Update("update t_banner set used=1 where id=#{id} and scope=2")
    int usedBanner(Integer id);

    @Update("update t_banner set used=0 where scope=2")
    int unsedBanner();

    @Select("select * from t_banner where id=#{id} and scope=2")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    Banner getBanner(Integer id);

    @Select("select * from t_banner where used=1 and scope=2")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    Banner getUsedBanner();

    @Select("select * from t_banner where used=1 and scope=2")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    List<Banner> getUsedListBanner();


    @Select("select * from t_banner where scope=2")
    @Results({
            @Result(column = "real_path", property = "realPath"),
            @Result(column = "relative_path", property = "relativePath")
    })
    List<Banner> listBanner();

    @Insert("insert into t_news values(null,#{title},#{imgRelativePath},#{imgRealPath},#{publishDate},#{describe})")
    int saveNews(News news);

    @Delete("delete from t_news where id=#{id}")
    int deleteNews(Integer id);

    @Update("update t_news set title=#{title},img_relative_path=#{imgRelativePath},img_real_path=#{imgRealPath},publish_date=#{publishDate},`describe`=#{describe} where id=#{id}")
    int updateNews(News news);

    @Select("select count(id) from t_news")
    int countNews();

    @Select("select * from t_news where id=#{id}")
    @Results({
            @Result(column = "img_real_path", property = "imgRealPath"),
            @Result(column = "img_relative_path", property = "imgRelativePath"),
            @Result(column = "publish_date",property = "publishDate")
    })
    News getNews(Integer id);

    @Select("select * from t_news where id = (select id from t_news where publish_date > #{date} order by publish_date asc limit 1);")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath"),
            @Result(column = "publish_date",property = "publishDate")
    })
    News getPreNews(LocalDate date);

    @Select("select * from t_news where id = (select id from t_news where publish_date < #{date} order by publish_date desc limit 1)")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath"),
            @Result(column = "publish_date",property = "publishDate")
    })
    News getNextNews(LocalDate date);

    @Select("select * from t_news order by publish_date desc limit #{page},#{limit}")
    @Results({
            @Result(column = "img_real_path", property = "imgRealPath"),
            @Result(column = "img_relative_path", property = "imgRelativePath"),
            @Result(column = "publish_date",property = "publishDate")
    })
    List<News> listPageNews(@Param("page") Integer page,@Param("limit") Integer limit);

    @Select("select * from t_news order by publish_date desc")
    @Results({
            @Result(column = "img_real_path", property = "imgRealPath"),
            @Result(column = "img_relative_path", property = "imgRelativePath"),
            @Result(column = "publish_date",property = "publishDate")
    })
    List<News> listAllNews();
}
