package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Model: Banner实体类
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/24 15:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banner {

    private Integer id;
    private String title;
    private String describe;
    private String realPath;
    private String relativePath;
    private Boolean used;
}
