package com.zf.website.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Model:Logo实体类
 * Author:Tr9(韩峰)
 * Description:Logo实体类
 * Time: 2019/9/23 13:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Logo {

    private Integer id;
    private String path;
    private LocalDateTime createTime;
}
