package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/16 10:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Integer id;
    private String title;
    private String imgRealPath;
    private String imgRelativePath;
    private String describe;
}
