package com.zf.website.mapper;

import com.zf.website.bean.Logo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/23 13:48
 */
@Mapper
public interface IndexMapper {

    @Insert("insert into t_logo values(null,#{path},#{createTime})")
    public int saveLogo(Logo logo);
}
