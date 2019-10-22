package com.zf.website.service.impl;

import com.zf.website.bean.Name;
import com.zf.website.mapper.NameMapper;
import com.zf.website.service.INameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/15 14:17
 */
@Service
@Transactional
public class NameService implements INameService {

    @Resource
    private NameMapper nameMapper;

    @Override
    public boolean saveName(Name name) {
        return nameMapper.saveName(name) > 0;
    }

    @Override
    public boolean deleteName(Integer id) {
        return nameMapper.deleteName(id) > 0;
    }

    @Override
    public boolean updateName(Name name) {
        return nameMapper.updateName(name) > 0;
    }

    @Override
    public Name getName() {
        return nameMapper.getName();
    }
}
