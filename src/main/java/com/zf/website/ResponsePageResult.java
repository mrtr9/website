package com.zf.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/9 12:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePageResult<T>{

    private Integer code;
    private  String msg;
    private Integer count;
    private T data;

}