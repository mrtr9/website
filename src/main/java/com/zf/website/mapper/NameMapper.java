package com.zf.website.mapper;

import com.zf.website.bean.Name;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NameMapper {

    @Insert("insert into t_name values(null,#{name})")
    int saveName(Name name);

    @Delete("delete from t_name where id=#{id}")
    int deleteName(Integer id);

    @Update("update t_name set `name`=#{name} where id=#{id}")
    int updateName(Name name);

    @Select("select * from t_name")
    Name getName();
}
