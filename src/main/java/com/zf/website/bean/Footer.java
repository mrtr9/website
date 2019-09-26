package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class Footer {

    private Integer id;
    private String telephone;
    private String email;
    private String address;
    private String copyRight;
    private String qrRealPath;
    private String qrRelativePath;
}
