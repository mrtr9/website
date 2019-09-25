package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/25 16:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Link {

    private Integer id;
    private String nickName;
    private String url;
    private Integer footerId;
}
