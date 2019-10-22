package com.zf.website.controller;

import com.zf.website.ResponseResult;
import com.zf.website.bean.Name;
import com.zf.website.service.INameService;
import com.zf.website.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/15 14:21
 */
@RestController
@RequestMapping("name")
public class NameController {

    @Autowired
    private INameService nameService;

    @PostMapping
    public ResponseResult saveName(Name name) {
        if (!BeanUtil.isNull(name)) {
            boolean flag = nameService.saveName(name);
            if (flag)
                return ResponseResult.builder().code(200).message("保存成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("保存失败").data(Boolean.FALSE).build();
    }

    @DeleteMapping
    public ResponseResult deleteName(Integer id) {
        if (id != null) {
            boolean flag = nameService.deleteName(id);
            if (flag)
                return ResponseResult.builder().code(200).message("删除成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("删除失败").data(Boolean.FALSE).build();
    }

    @PutMapping
    public ResponseResult updateName(Name name) {
        if (!BeanUtil.isNull(name)) {
            boolean flag = nameService.updateName(name);
            if (flag)
                return ResponseResult.builder().code(200).message("修改成功").data(Boolean.TRUE).build();
        }
        return ResponseResult.builder().code(200).message("修改失败").data(Boolean.FALSE).build();
    }

    @GetMapping
    public ResponseResult getName(){
        Name name = nameService.getName();
        if(!BeanUtil.isNull(name))
            return ResponseResult.builder().code(200).message("查询成功").data(name).build();
        return ResponseResult.builder().code(200).message("数据为空").data(null).build();
    }
}
