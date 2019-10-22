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
 * Time: 2019/10/22 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Case {

    private Integer id;
    private String title;
    private String imgRelativePath;
    private String imgRealPath;
    private String describe;
    private LocalDate date;

}
