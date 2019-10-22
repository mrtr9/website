package com.zf.website.service;

import com.zf.website.bean.Name;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/15 14:16
 */
public interface INameService {

    boolean saveName(Name name);

    boolean deleteName(Integer id);

    boolean updateName(Name name);

    Name getName();
}
