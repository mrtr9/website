package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/15 14:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Name {

    private Integer id;
    private String name;
}
