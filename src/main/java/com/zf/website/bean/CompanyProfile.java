package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/29 11:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyProfile {
    private Integer id;
    private String content;
}
