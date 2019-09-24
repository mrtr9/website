package com.zf.website.service.impl;

import com.zf.website.bean.User;
import com.zf.website.mapper.UserMapper;
import com.zf.website.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Model: 用户类业务逻辑层
 * Author:Tr9(韩峰)
 * Description: 用户类业务逻辑层，包含登陆、修改密码等操作
 * Time: 2019/9/20 12:56
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
