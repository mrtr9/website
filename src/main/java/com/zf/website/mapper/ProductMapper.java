package com.zf.website.mapper;

import com.zf.website.bean.Banner;
import com.zf.website.bean.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into t_banner values(null,#{title},#{describe},#{realPath},#{relativePath},#{used},1)")
    int saveBanner(Banner banner);

    @Delete("delete from t_banner where id=#{id} and scope=1")
    int deleteBanner(Integer id);

    @Update("update t_banner set title=#{title},`describe`=#{describe} where id=#{id} and scope=1")
    int updateBanner(Banner banner);

    @Update("update t_banner set used=1 where id=#{id} and scope=1")
    int usedBanner(Integer id);

    @Update("update t_banner set used=0 where scope=1")
    int unsedBanner();

    @Select("select * from t_banner where id=#{id} and scope=1")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    Banner getBanner(Integer id);

    @Select("select * from t_banner where used=1 and scope=1")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    Banner getUsedBanner();

    @Select("select * from t_banner where used=1 and scope=1")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    List<Banner> getUsedListBanner();

    @Select("select * from t_banner where scope=1")
    @Results({
            @Result(column = "real_path",property = "realPath"),
            @Result(column = "relative_path",property = "relativePath")
    })
    List<Banner> listBanner();

    @Insert("insert into t_product values(null,#{title},#{imgRealPath},#{imgRelativePath},#{describe})")
    int saveProduct(Product product);

    @Delete("delete from t_product where id=#{id}")
    int deleteProduct(Integer id);

    @Update("update t_product set title=#{title},img_real_path=#{imgRealPath},img_relative_path=#{imgRelativePath},`describe`=#{describe} where id=#{id}")
    int updateProduct(Product product);

    @Select("select count(id) from t_product")
    int countProduct();

    @Select("select * from t_product")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    List<Product> listAllProduct();

    @Select("select * from t_product limit #{page},#{limit}")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    List<Product> listPageProduct(@Param("page") Integer page,@Param("limit") Integer limit);

    @Select("select * from t_product where id=#{id}")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    Product getProduct(Integer id);

    @Select("select * from t_product where id = (select id from t_product where id < #{id} order by id desc limit 1);")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    Product getPreProduct(Integer id);

    @Select("select * from t_product where id = (select id from t_product where id > #{id} order by id asc limit 1);")
    @Results({
            @Result(column = "img_relative_path",property = "imgRelativePath"),
            @Result(column = "img_real_path",property = "imgRealPath")
    })
    Product getNextProduct(Integer id);
}
