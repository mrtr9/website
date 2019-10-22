package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/9 11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recruit {

    private Integer id;
    private String title;
    private String describe;
}
