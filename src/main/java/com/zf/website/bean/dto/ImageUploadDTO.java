package com.zf.website.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/29 10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUploadDTO {

    private String title;
    private String src;
    private String realPath;
}
