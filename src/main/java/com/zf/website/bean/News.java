package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/21 10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    private Integer id;
    private String title;
    private String imgRelativePath;
    private String imgRealPath;
    private LocalDate publishDate;
    private String describe;

}
