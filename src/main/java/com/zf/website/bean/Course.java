package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/10 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    private Integer id;
    private LocalDate time;
    private String imgRelativePath;
    private String imgRealPath;
    private String describe;

}
