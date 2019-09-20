package com.zf.website.mapper;

import com.zf.website.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Model: 用户Mapper
 * Author:Tr9(韩峰)
 * Description: 包含登陆、修改密码等操作
 * Time: 2019/9/20 12:53
 */
@Mapper
public interface UserMapper {

    @Select("select * from t_user where username=#{username} and password=#{password}")
    public User login(User user);

}
