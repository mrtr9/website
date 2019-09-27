package com.zf.website.mapper;

import com.zf.website.bean.Footer;
import com.zf.website.bean.Link;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FooterMapper {

    @Insert("insert into t_footer values(null,#{telephone},#{email},#{address},#{copyRight},#{qrRealPath},#{qrRelativePath})")
    int saveFooter(Footer footer);

    @Delete("delete from t_footer")
    int deleteAllFooter();

    @Update("update t_footer set telephone=#{telephone},email=#{email},address=#{address},copy_right=#{copyRight},qr_real_path=#{qrRealPath},qr_relative_path=#{qrRelativePath} where id=#{id}")
    int updateFooter(Footer footer);

    @Select("select * from t_footer")
    @Results({
            @Result(column = "copy_right",property = "copyRight"),
            @Result(column = "qr_real_path",property = "qrRealPath"),
            @Result(column = "qr_relative_path",property = "qrRelativePath")
    })
    Footer getFooter();

    @Insert("insert into t_link values(null,#{nickName},#{url})")
    int saveLink(Link link);

    @Insert("<script>insert into t_link(nick_name,url) values <foreach item='link' index='key' collection='links' separator=','>(#{link.nickName},#{link.url})</foreach></script>")
    int saveLinks(@Param("links") List<Link> links);

    @Delete("delete from t_link where id=#{id}")
    int deleteLink(Integer id);

    @Delete("<script>delete from t_link where id in <foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int deleteLinks(@Param("ids") Integer[] ids);

    @Delete("delete from t_link")
    int deleteAllLinks();

    @Update("update t_link set nick_name=#{nickName},url=#{url} where id=#{id}")
    int updateLink(Link link);

    @Update("<script><foreach collection='links' item='link' separator=';'></foreach></script>")
    int updateLinks(@Param("links") List<Link> links);

    @Select("select * from t_link")
    @Results({
            @Result(column = "nick_name",property = "nickName")
    })
    List<Link> listLinks();
}
